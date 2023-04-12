package edu.utsa.cs3443.theguidesgrandadventure.Model;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;

public class GameThread extends Thread {
    private GameActivity activity;
    private boolean isRunning;
    private int initInterval;

    public GameThread(GameActivity activity){
        this.activity = activity;
        initInterval = 500;
        activity.getGameCanvas().setScoreCount(0);
    }

    public void run(){
        while(this.isRunning){
            try {
                synchronized (activity.getGameCanvas()) {
                    activity.getGameCanvas().update();
                    activity.getGameCanvas().invalidate();
                }
            } catch (Exception e) {
            }

            try {
                this.sleep(calcInterval());
            } catch (Exception e) {
            }
        }
    }

    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
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
}
