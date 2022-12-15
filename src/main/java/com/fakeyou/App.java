package com.fakeyou;

import java.util.List;

/**
 * Test App
 */
public class App 
{
    public static void main( String[] args )
    {

        FakeYouClient client = new FakeYouClient();

        try {
            // use this if you're a subscriber and want use your priority privileges
            // client.login("[user]]", "[pwd]]");
            // get all vices
            List<TTSVoice> voices = client.getVoices();
            if (voices == null || voices.isEmpty()) {
                return;
            }
            // get random voice
            TTSVoice voice = voices.get(Math.abs((int)System.currentTimeMillis() % voices.size()));

            long start = System.currentTimeMillis();
            byte[] data = client.getAudioBytes("Hello, this is a test", voice);
            if (data != null) {
                System.out.printf("received audio data %d bytes\n", data.length);
            } else {
                System.out.println("unable to load audio file");
            }
            long end = System.currentTimeMillis();
            System.out.printf("fakeyou request took %dms\n", end - start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
