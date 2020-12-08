package uet.oop.bomberman.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SimpleAudioPlayer {

    Clip clip;
    String status;
    Long currentFrame;
    static String filePath;
    AudioInputStream audioInputStream;

    public SimpleAudioPlayer(String filePath) {
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void playWhenDie() {
        try {
            String filePath = "res/audio/AA126_11.wav";
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
            audioPlayer.play();
        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }

    public static void playWhenEatItem() {
        try {
            String filePath = "res/audio/Item.wav";
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
            audioPlayer.play();
        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }

    public static void playWhenExplosive() {
        try {
            String filePath = "res/audio/BOM_11_M.wav";
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
            audioPlayer.play();
        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }

    public static void playWhenPutBom() {
        try {
            String filePath = "res/audio/BOM_SET.wav";
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
            audioPlayer.play();
        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }

    public static void playWhenNotYetExplosive() {
        try {
            String filePath = "res/audio/BOSS_X_11K.wav";
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
            audioPlayer.play();
        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }


    public static void playWhenEndGame() {
        try {
            String filePath = "res/audio/endgame3.wav";
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
            audioPlayer.play();
        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }

    public static void playWhenPlayGame() {
        try {
            String filePath = "res/audio/soundtrack.wav";
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
            audioPlayer.play();
            audioPlayer.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }


    public void play() {
        clip.start();
        status = "play";
    }

    public void pause() {
        if (status.equals("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        if (status.equals("play")) {
            System.out.println("Audio is already " +
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void restart() {
        try {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = 0L;
            clip.setMicrosecondPosition(0);
            this.play();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void jump(long c) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        if (c > 0 && c < clip.getMicrosecondLength()) {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}