package cn.afternode.commonutil.io;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Sound {
    private final FileInputStream is;
    private final AudioStream as;

    public Sound(File file) throws IOException {
        is = new FileInputStream(file);
        as = new AudioStream(is);
    }

    public FileInputStream getInputStream() {
        return is;
    }

    public void play() {
        AudioPlayer.player.start(as);
    }

    public void stop() {
        AudioPlayer.player.stop(as);
    }

    public int getLength() {
        return as.getLength();
    }
}
