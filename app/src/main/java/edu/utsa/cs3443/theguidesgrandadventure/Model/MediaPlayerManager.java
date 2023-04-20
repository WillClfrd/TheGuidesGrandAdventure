package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;

import java.io.IOException;

public class MediaPlayerManager {
    private static MediaPlayerManager instance;
    private MediaPlayer mediaPlayer;
    private final Resources resources;
    private int currentResourceId;
    public static boolean isPlaying;

    private MediaPlayerManager(Context context) {
        resources = context.getResources();
    }

    public static synchronized MediaPlayerManager getInstance(Context context) {
        if (instance == null) {
            instance = new MediaPlayerManager(context);
        }
        return instance;
    }

    public void playMusic(int resourceId) {
        if (resourceId != currentResourceId) {
            try {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                } else {
                    if (isPlaying) {
                        mediaPlayer.stop();
                        isPlaying = false;
                    }
                    mediaPlayer.reset();
                }
                mediaPlayer.setDataSource(resources.openRawResourceFd(resourceId));
                mediaPlayer.prepare();
                currentResourceId = resourceId;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0.02f,0.02f);
        mediaPlayer.start();
        isPlaying = true;
    }

    public void pauseMusic() {
        if (mediaPlayer != null && isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isPlaying = false;
        }
    }

    public void releaseMediaPlayer() {
        stopMusic();
    }

    public boolean isPlaying() {
        return isPlaying;
    }


    public void toggleSound() {
        if(isPlaying) {
            mediaPlayer.setVolume(0f,0f);
            isPlaying = false;
        }
        else {
            mediaPlayer.setVolume(0.02f,0.02f);
            isPlaying = true;
        }
    }
}