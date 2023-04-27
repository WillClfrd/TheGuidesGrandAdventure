package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * MediaPlayerManager.java is a class that creates a MediaPlayer object and manages its use
 * across the different views of the app, more specifically for playing/pausing or changing
 * the audio being played.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
public class MediaPlayerManager {

    private static MediaPlayerManager instance;
    private MediaPlayer mediaPlayer;
    private final Resources resources;
    private int currentResourceId;
    public static boolean isPlaying = true;

    /**
     * Class constructor that creates a MediaPlayerManager.
     *
     * @param context - Context object that is used to open resources.
     */
    private MediaPlayerManager(Context context) {
        resources = context.getResources();
    }

    /**
     * Gets instance of Mediaplayer for the activity calling it.
     *
     * @param context - Context object that is used to open resources.
     */
    public static synchronized MediaPlayerManager getInstance(Context context) {
        if (instance == null) {
            instance = new MediaPlayerManager(context);
        }
        return instance;
    }

    /**
     * Enables MediaPlayer object to play audio for the app, if the global boolean is set to true
     * (changed in settings).
     *
     * @param resourceId - Integer id of the specific audio file passed.
     */
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
        mediaPlayer.setVolume(0.25f,0.25f); // Too loud :(
        mediaPlayer.start();
        isPlaying = true;
    }

    /**
     * Toggles whether the Mediaplayer is playing sound or not. (Accessed by settings)
     */
    public void toggleSound() {
        if(isPlaying) {
            mediaPlayer.setVolume(0f,0f);
            isPlaying = false;
        }
        else {
            mediaPlayer.setVolume(0.25f,0.25f);
            isPlaying = true;
        }
    }

    /**
     * Releases MediaPlayer object, normally upon exit of the entire app.
     */
    public void releasePlayer() {
        mediaPlayer.release();
    }
}