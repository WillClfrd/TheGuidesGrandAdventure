package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Intent;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.GameOverActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

/**
 * This class represents CharacterThread objects.
 * The CharacterThread class maintains the activity, isRunning, isPaused, initInterval, and soundManager attributes.
 * The CharacterThread class extends the Thread class.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
public class CharacterThread extends Thread {
    private final GameActivity activity;
    private boolean isRunning;
    private boolean isPaused;
    private final int initInterval;
    private final SoundManager soundManager;

    /**
     * Creates a CharacterThread object.
     * Initializes the activity, soundManager, isRunning, initInterval, and isPaused attributes.
     *
     * @param activity the GameActivity object to be assigned to the activity attribute.
     */
    public CharacterThread(GameActivity activity){
        this.activity = activity;
        this.soundManager = new SoundManager(activity);
        this.isRunning = false;
        this.initInterval = 500;
        activity.getGameCanvas().setScoreCount(0);
        this.isPaused = false;
    }

    /**
     * Starts the CharacterThread when called and continues running the CharacterThread according to the isRunning attribute.
     */
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
                    activity.getGameCanvas().walkCharacter(activity.getGameCanvas().getCharacter());
                    activity.getGameCanvas().invalidate();
                }
            } catch (Exception ignored) {
            }

            try {
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

    /**
     * Sets the value of the CharacterThread isRunning attribute to the specified value.
     *
     * @param isRunning the value to be assigned to the CharacterThread isRunning attribute.
     */
    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    /**
     * Sets the value of the CharacterThread isPaused attribute to the specified value.
     *
     * @param isPaused the value to be assigned to the CharacterThread isPaused attribute.
     */
    public void setIsPaused(boolean isPaused){
        this.isPaused = isPaused;
    }

    /**
     * Determines the interval that will be passed into the sleep method call in the run method based on the activity GameCanvas object scoreCount attribute value.
     *
     * @return the calculated integer value for the interval.
     */
    private int calcInterval(){
        return Math.max(initInterval - (50 * (activity.getGameCanvas().getScoreCount() / 10)), 50);
    }

    /**
     * Returns the CharacterThread isRunning attribute.
     *
     * @return the value of the CharacterThread isRunning attribute.
     */
    public boolean getIsRunning() {
        return this.isRunning;
    }
}
