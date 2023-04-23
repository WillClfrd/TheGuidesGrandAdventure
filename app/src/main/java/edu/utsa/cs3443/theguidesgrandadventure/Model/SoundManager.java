package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Context;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.Map;

import edu.utsa.cs3443.theguidesgrandadventure.R;

/**
 * SoundManager.java is a class that creates a SoundManager object and manages its use
 * across the different views of the app, more specifically for playing sounds depending on
 * button interactions or game interactions.
 *
 * @author Jose Gracia
 * @version 1.0
 * @since 2023-04-22
 */
@SuppressWarnings("ALL")
public class SoundManager {

    private static final int MAX_STREAMS = 5;
    private static final int DEFAULT_PRIORITY = 1;
    private static final int DEFAULT_LOOP = 0;
    private static final float DEFAULT_RATE = 1f;
    static boolean soundPlaying = true;
    private final Context context;
    private final SoundPool soundPool;

    private final Map<Integer, Integer> soundIds = new HashMap<>();

    /**
     * Class constructor that creates a SoundManager.
     *
     * @param context - Context object that is used to open resources.
     */
    public SoundManager(Context context) {
        this.context = context;
        soundPool = new SoundPool.Builder()
                .setMaxStreams(MAX_STREAMS)
                .build();
        loadSounds();
    }

    /**
     * loadSounds loads sounds for use across the activities of the program.
     */
    private void loadSounds() {
        soundIds.put(R.raw.pointget, soundPool.load(context, R.raw.pointget, DEFAULT_PRIORITY));
        soundIds.put(R.raw.footstep, soundPool.load(context, R.raw.footstep, DEFAULT_PRIORITY));
        soundIds.put(R.raw.boundaryhit, soundPool.load(context, R.raw.boundaryhit, DEFAULT_PRIORITY));
        soundIds.put(R.raw.upgrade, soundPool.load(context, R.raw.upgrade, DEFAULT_PRIORITY));
    }

    /**
     * playSound plays a sound depending on the soundId int passed through the method.
     *
     * @param soundId - Integer id of the R.raw.sound object.
     */
    public void playSound(int soundId) {
        if(soundPlaying) {
            if(soundId == R.raw.footstep) {
                soundPool.play(soundIds.get(soundId), 0.1f, 0.1f, DEFAULT_PRIORITY, 0, 0.65f);
            }
            else{
                soundPool.play(soundIds.get(soundId), 0.25f, 0.25f, DEFAULT_PRIORITY, DEFAULT_LOOP, DEFAULT_RATE);
            }
        }
    }

    /**
     * Sound on/off toggle.
     */
    public static void setSound() {
        soundPlaying = !soundPlaying;
    }
}