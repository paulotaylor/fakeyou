package com.fakeyou;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.ByteArrayEntity;

import java.io.*;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.WARNING;

/**
 * FakeYou Java Client
 */
public class FakeYouClient {

    private static final Logger logger = Logger.getLogger(FakeYouClient.class.getName());

    private final ObjectMapper mapper = new ObjectMapper();
    private String sessionCookie;

    /**
     * Returns JSON String from url
     * @param url url endpoint
     * @param data post data
     * @return JSON String
     * @throws IOException Network errors
     */
    private String getJson(URL url, byte [] data) throws IOException {
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        if (sessionCookie != null && sessionCookie.length() > 0) {
            connection.setRequestProperty("Cookie", "session=" + sessionCookie);
        }
        connection.setRequestProperty("Content-Type", "application/json");
        if (data != null) {
            connection.setDoOutput(true);
            connection.getOutputStream().write(data);
        }
        try (InputStream input = connection.getInputStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        }
    }


    /**
     * Executes login endpoint and store session cookie
     * @param user account user
     * @param password account password
     * @return true if login is successful
     * @throws IOException Network errors
     * @throws IllegalStateException If login fails
     */
    public boolean login(String user, String password) throws IOException {
        byte [] data = mapper.writeValueAsBytes(new TTSLoginRequest(user, password));
        HttpClientBuilder builder = HttpClientBuilder.create();
        try(CloseableHttpClient http = builder.build()) {

            HttpPost httpRequest = new HttpPost("https://api.fakeyou.com/login");

            HttpEntity entity = new ByteArrayEntity(data, ContentType.APPLICATION_JSON);
            httpRequest.setEntity(entity);
            http.execute(httpRequest, classicHttpResponse -> {
                byte [] responseData = getStreamData(classicHttpResponse.getEntity().getContent());
                logger.log(WARNING, "FakeYou login response " + new String(responseData));

                Header[] headers = classicHttpResponse.getHeaders("set-cookie");

                if (headers != null) {
                    for (Header h : headers) {
                        /* check cookies */
                        List<HttpCookie> cookies = HttpCookie.parse(h.getValue());

                        for (HttpCookie c : cookies) {
                            if ("session".equals(c.getName())) {
                                sessionCookie = c.getValue();
                                return true;
                            }
                        }
                    }
                }
                return classicHttpResponse;
            });
        }
        return false;
    }


    /**
     * Read bytes from input stream
     * @param is input stream to read the data from
     * @return data read from the input stream
     * @throws IOException network error
     */
    private byte [] getStreamData(InputStream is) throws IOException{
        if (is == null){
            return null;
        }
        try (is; ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int read;
            byte[] buffer = new byte[4096];
            while ((read = is.read(buffer)) > 0) {
                out.write(buffer, 0, read);
            }
            return out.toByteArray();
        }
    }

    /**
     * Convert text to audio
     * @param text text to convert to speech
     * @param voice voice to use for text to speech
     * @return audio data
     * @see #getAudioBytes(String, String)
     * @see #getVoices()
     */
    public byte[] getAudioBytes(String text, TTSVoice voice) {
        return getAudioBytes(text, voice.getModelToken());
    }
    /**
     * Convert text to audio
     * @param text text to convert to speech
     * @param voice voice model token
     * @return audio data
     * @see #getAudioBytes(String, String)
     * @see #getVoices()
     */
    public byte[] getAudioBytes(String text, String voice) {
        String url = null;
        try {
            url = getAudioUrl(text, voice);
            if (url != null && !url.isEmpty()) {
                return getStreamData(new URL(url).openConnection().getInputStream());
            }
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error loading fakeyou url " + url, e);
        }
        return null;
    }


    /**
     * Convert text to audio
     * @param text text to convert to speech
     * @param voice voice model token
     * @return audio url location
     * @throws IOException network errors
     * @see #getVoices()
     */
    public String getAudioUrl(String text, TTSVoice voice) throws IOException {
        return getAudioUrl(text, voice.getModelToken());
    }
    /**
     * Convert text to audio
     * @param text text to convert to speech
     * @param voice voice model token
     * @return audio url location
     * @throws IOException network errors
     * @see #getVoices()
     */
    public String getAudioUrl(String text, String voice) throws IOException {

        TTSRequest request = new TTSRequest();
        request.setInferenceText(text);
        request.setTtsModelToken(voice);
        request.setUuidIdempotencyToken(UUID.randomUUID().toString());

        URL url = new URL("https://api.fakeyou.com/tts/inference");

        String json = getJson(url, mapper.writeValueAsBytes(request));
        logger.info("fakeyou response " + json);

        TTSResponse response = mapper.readValue(json.getBytes(), TTSResponse.class);

        String token = response.getInferenceJobToken();
        if (response.getSuccess() != Boolean.TRUE || token.isEmpty()) {
            logger.warning("fakeyou request failed");
            return null;
        }
        String out = null;
        try {
            long start = System.currentTimeMillis();
            final long timeout = 60000;
            do {
                Thread.sleep(1000);
                if (System.currentTimeMillis() - start > timeout) {
                    logger.info("fakeyou timed out " + token);
                    break;
                }
                JobResponse jobResponse = mapper.readValue(getJson(new URL("https://api.fakeyou.com/tts/job/" + token), null), JobResponse.class);
                State state = jobResponse.getState();
                String requestStatus = state.getStatus();
                logger.info("fakeyou job response status " + requestStatus);
                if ("complete_failure".equals(requestStatus)) {
                    logger.fine("fakeyou request failure " + token);
                    break;
                }
                if ("attempt_failed".equals(requestStatus)) {
                    logger.fine("fakeyou request failed " + token);
                    break;
                }
                if ("dead".equals(requestStatus)) {
                    logger.fine("fakeyou request dead " + token);
                    break;
                }
                if ("complete_success".equals(requestStatus) || "complete".equals(requestStatus)) {
                    logger.fine("fakeyou request success " + token);
                    String path = state.getMaybePublicBucketWavAudioPath();
                    out = "https://storage.googleapis.com/vocodes-public" + path;
                }
            } while(out == null);
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error checking fakeyou token", e);
        }
        return out;
    }

    /**
     * Retrieve list of all voices
     * @return all voices available or null if an error occurs
     */
    public List<TTSVoice> getVoices() {
        try {
            TTSVoicesResponse response = mapper.readValue(new URL("https://api.fakeyou.com/tts/list"), TTSVoicesResponse.class);
            if (response != null && response.getSuccess()) {
                return response.getModels();
            }
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error loading FakeYou voices", e);
        }
        return null;
    }
    /**
     * Retrieve list of all voices
     * @return all voices available or null if an error occurs
     */
    public List<TTSCategory> getCategories() {
        try {
            TTSCategoriesResponse response = mapper.readValue(new URL("https://api.fakeyou.com/category/list/tts "), TTSCategoriesResponse.class);
            if (response != null && response.getSuccess()) {
                return response.getCategories();
            }
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error loading FakeYou voices", e);
        }
        return null;
    }
}
