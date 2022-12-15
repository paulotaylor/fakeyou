# fakeyou-java

This is a Java wrapper for the [Fake You API](https://docs.fakeyou.com/)

This Library is released under the GNU General Public License

Use it at your own risk!


```
    FakeYouClient client = new FakeYouClient();

    try {
        // use this if you're a subscriber and want use your priority privileges
        // client.login("[user]", "[pwd]");
        // get all vices
        List<TTSVoice> voices = client.getVoices();
        if (voices == null || voices.isEmpty()) {
            return;
        }
        // get random voice
        TTSVoice voice = voices.get(Math.abs((int)System.currentTimeMillis() % voices.size()));

        byte[] data = client.getAudioBytes("Hello, this is a test", voice);
        if (data != null) {
            System.out.printf("received audio data %d bytes\n", data.length);
        } else {
            System.out.println("unable to load audio file");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
```
