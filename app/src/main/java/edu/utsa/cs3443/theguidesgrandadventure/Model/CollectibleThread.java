package edu.utsa.cs3443.theguidesgrandadventure.Model;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;

/**
 * This class represents CollectibleThread objects.
 * The CollectibleThread class maintains the activity, isRunning, isPaused, and interval attributes.
 * The CollectibleThread class extends the Thread class.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
public class CollectibleThread extends Thread {
    private final GameActivity activity;
    private boolean isRunning;
    private boolean isPaused;
    private final int interval;

    /**
     * Creates a CollectibleThread object.
     * Initializes the activity, interval, and isPaused attributes.
     *
     * @param activity the GameActivity object to be assigned to the activity attribute.
     */
    public CollectibleThread(GameActivity activity){
        this.activity = activity;
        this.interval = 50;
        this.isPaused = false;
    }

    /**
     * Starts the CollectibleThread when called and continues running the CharacterThread according to the isRunning attribute.
     */
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
            } catch (Exception ignored) {
            }

            try {
                sleep(interval);
            } catch (Exception ignored) {
            }
            activity.getGameCanvas().setHasCollectible(!(activity.getGameCanvas().objectCollisionCheck(activity.getGameCanvas().getCharacter(), activity.getGameCanvas().getCollectible())));
            isRunning = activity.getCharacterThread().getIsRunning();
        }
    }

    /**
     * Sets the value of the CollectibleThread isRunning attribute to the specified value.
     *
     * @param isRunning the value to be assigned to the CollectibleThread isRunning attribute.
     */
    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    /**
     * Sets the value of the CollectibleThread isPaused attribute to the specified value.
     *
     * @param isPaused the value to be assigned to the CollectibleThread isPaused attribute.
     */
    public void setIsPaused(boolean isPaused){
        this.isPaused = isPaused;
    }
}
