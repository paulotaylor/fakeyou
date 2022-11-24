package com.fakeyou;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FakeYou Java Client
 */
public class FakeYouClient {

    private static final Logger logger = Logger.getLogger(FakeYouClient.class.getName());

    private final ObjectMapper mapper = new ObjectMapper();

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
        if (data != null) {
            connection.setRequestProperty("Content-Type", "application/json");
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
     * @throws IOException network errors
     * @see #getAudioBytes(String, String)
     * @see #getVoices()
     */
    public byte[] getAudioBytes(String text, TTSVoice voice) throws IOException {
        return getAudioBytes(text, voice.getModelToken());
    }
    /**
     * Convert text to audio
     * @param text text to convert to speech
     * @param voice voice model token
     * @return audio data
     * @throws IOException network errors
     * @see #getVoices()
     */
    public byte[] getAudioBytes(String text, String voice) throws IOException {

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
        try {
            long start = System.currentTimeMillis();
            final long timeout = 60000;
            String out = null;
            do {
                Thread.sleep(1000);
                if (System.currentTimeMillis() - start > timeout) {
                    logger.info("fakeyou timed out " + token);
                    break;
                }
                JobResponse jobResponse = mapper.readValue(new URL("https://api.fakeyou.com/tts/job/" + token), JobResponse.class);
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
                    out = state.getMaybePublicBucketWavAudioPath();
                    break;
                }
            } while(out == null || out.isEmpty());
            if (out != null && !out.isEmpty()) {
                return getStreamData(new URL("https://storage.googleapis.com/vocodes-public" + out).openConnection().getInputStream());
            }
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error checking fakeyou token", e);
        }
        return null;
    }

    /**
     * Retrieve list of all voices
     * @return all voices available or null if an error occurs
     */
    public List<TTSVoice> getVoices() {
        try {
            return mapper.readerForListOf(TTSVoice.class).readValue(new URL("https://api.fakeyou.com/tts/list"));
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error loading FakeYou voices", e);
        }
        return null;
    }
}
