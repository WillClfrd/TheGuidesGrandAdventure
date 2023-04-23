package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Intent;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.GameOverActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

public class CharacterThread extends Thread {
    private final GameActivity activity;
    private boolean isRunning;
    private boolean isPaused;
    private final int initInterval;
    private SoundManager soundManager;
    private String key = "score";

    public CharacterThread(GameActivity activity){
        this.activity = activity;
        this.soundManager = new SoundManager(activity);
        this.isRunning = false;
        initInterval = 500;
        activity.getGameCanvas().setScoreCount(0);
        this.isPaused = false;
    }

    public void run(){
        while(this.isRunning){
            if(this.isPaused){
                continue;
            }
            try {
                synchronized (activity.getGameCanvas()) {
                    if(!(activity.getGameCanvas().updateCharacters())){
                        isRunning = false;
                    }
                    activity.getGameCanvas().invalidate();
                }
            } catch (Exception ignored) {
            }

            try {
                //noinspection BusyWait
                sleep(calcInterval());
                if(SoundManager.soundPlaying) {
                    soundManager.playSound(R.raw.footstep);
                }

            } catch (Exception ignored) {
            }
            if(isRunning) {
                isRunning = !activity.getGameCanvas().boundaryCollisionCheck(activity.getGameCanvas().getCharacter());
            }
        }
        Intent endScreenIntent = new Intent(activity, GameOverActivity.class);
        String key = "score";
        endScreenIntent.putExtra(key, activity.getGameCanvas().getScoreCount());
        activity.startActivity(endScreenIntent);
    }

    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void setIsPaused(boolean isPaused){
        this.isPaused = isPaused;
    }

    private int calcInterval(){

        return Math.max(initInterval - (50 * (activity.getGameCanvas().getScoreCount() / 10)), 50);
    }

    public boolean getIsRunning() {
        return this.isRunning;
    }
}
