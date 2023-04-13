package edu.utsa.cs3443.theguidesgrandadventure.Model;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;

public class CollectibleThread extends Thread {
    private GameActivity activity;
    private boolean isRunning;
    private boolean isPaused;
    private int interval;

    public CollectibleThread(GameActivity activity){
        this.activity = activity;
        this.interval = 50;
        this.isPaused = false;
    }

    public void run(){
        while(this.isRunning){
            if(this.isPaused){
                continue;
            }
            try {
                synchronized (activity.getGameCanvas()) {
                    activity.getGameCanvas().updateCollectibles();
                    activity.getGameCanvas().invalidate();
                }
            } catch (Exception e) {
            }

            try {
                this.sleep(interval);
            } catch (Exception e) {
            }
            activity.getGameCanvas().setHasCollectible(!(activity.getGameCanvas().objectCollisionCheck(activity.getGameCanvas().getCharacter(), activity.getGameCanvas().getCollectible())));
            isRunning = activity.getCharacterThread().getIsRunning();
        }
    }

    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void setIsPaused(boolean isPaused){
        this.isPaused = isPaused;
    }
}
