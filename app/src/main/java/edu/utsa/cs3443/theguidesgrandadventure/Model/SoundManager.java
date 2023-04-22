package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Context;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.Map;

import edu.utsa.cs3443.theguidesgrandadventure.R;

public class SoundManager {

    private static final int MAX_STREAMS = 5;
    private static final int DEFAULT_PRIORITY = 1;
    private static final int DEFAULT_LOOP = 0;
    private static final float DEFAULT_RATE = 1f;
    private static final int DEFAULT_QUALITY = 0;
    static boolean soundPlaying = true;
    private final Context context;
    private final SoundPool soundPool;

    private final Map<Integer, Integer> soundIds = new HashMap<>();
    public SoundManager(Context context) {
        this.context = context;
        soundPool = new SoundPool.Builder()
                .setMaxStreams(MAX_STREAMS)
                .build();
        loadSounds();
    }

    private void loadSounds() {
        soundIds.put(R.raw.pointget, soundPool.load(context, R.raw.pointget, DEFAULT_PRIORITY));
        soundIds.put(R.raw.footstep, soundPool.load(context, R.raw.footstep, DEFAULT_PRIORITY));
        soundIds.put(R.raw.boundaryhit, soundPool.load(context, R.raw.boundaryhit, DEFAULT_PRIORITY));
        soundIds.put(R.raw.upgrade, soundPool.load(context, R.raw.upgrade, DEFAULT_PRIORITY));
    }

    public void playSound(int soundId) {
        if(soundPlaying) {
            if(soundId == R.raw.footstep) {
                soundPool.play(soundIds.get(soundId), 0.25f, 0.25f, DEFAULT_PRIORITY, 0, 0.5f);
            }
            else{
                soundPool.play(soundIds.get(soundId), 0.25f, 0.25f, DEFAULT_PRIORITY, DEFAULT_LOOP, DEFAULT_RATE);
            }
        }
    }

    public static void setSound() {
        if(soundPlaying) {
            soundPlaying = false;
        }
        else {
            soundPlaying = true;
        }
    }

    public void release() {
        soundPool.release();
    }
}