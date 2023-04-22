package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Intent;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.GameOverActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

public class CharacterThread extends Thread {
    private GameActivity activity;
    private boolean isRunning;
    private boolean isPaused;
    private int initInterval;

    private SoundManager soundManager;
    private String key = "score";

    public CharacterThread(GameActivity activity){
        this.activity = activity;
        this.soundManager = new SoundManager(activity);
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
            } catch (Exception e) {
            }

            try {
                this.sleep(calcInterval());
                if(SoundManager.soundPlaying) {
                    soundManager.playSound(R.raw.footstep);
                }
            } catch (Exception e) {
            }
            if(isRunning) {
                isRunning = !activity.getGameCanvas().boundaryCollisionCheck(activity.getGameCanvas().getCharacter());
            }
        }
        Intent endScreenIntent = new Intent(activity, GameOverActivity.class);
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
        int temp;

        if(initInterval - (50 * (activity.getGameCanvas().getScoreCount() / 10)) <= 50){
            temp = 50;
        }
        else{
            temp = initInterval - (50 * (activity.getGameCanvas().getScoreCount() / 10));
        }

        return temp;
    }

    public boolean getIsRunning() {
        return this.isRunning;
    }
}
