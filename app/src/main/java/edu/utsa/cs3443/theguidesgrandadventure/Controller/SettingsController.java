package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import android.view.View;
import android.widget.Toast;

import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;
import edu.utsa.cs3443.theguidesgrandadventure.Model.SoundManager;
import edu.utsa.cs3443.theguidesgrandadventure.R;
import edu.utsa.cs3443.theguidesgrandadventure.SettingsActivity;

/**
 * The SettingsController class implements the OnClickListener and controls events for
 * SettingsActivity. The class also holds a class constructor and onClick for button interactions.
 *
 * @author Jose Gracia
 * @version 1.0
 * @since 2023-04-20
 */
public class SettingsController implements View.OnClickListener {

    private final SettingsActivity activity;

    private boolean soundPlaying = true;

    private boolean musicPlaying = true;

    public static int bkgId = 0;

    /**
     * Constructor method for SettingsController.
     *
     * @param activity - This is the activity of SettingsActivity class.
     */
    public SettingsController(SettingsActivity activity) {
        this.activity = activity;
    }

    /**
     * onClick method for SettingsController, it controls interactions for the
     * SettingsActivity.class.
     *
     * @param view - This is a view item from the settings xml file.
     */
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.return_button) {
            activity.finish(); // This will end the settings activity.
        }
        else if(view.getId() == R.id.music_button) {
            MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance(activity);
            musicPlaying = !musicPlaying;

            Toast.makeText(activity, "Music: " + (musicPlaying? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            mediaPlayerManager.toggleSound();
        }
        else if(view.getId() == R.id.sound_button) {
            soundPlaying = !soundPlaying;
            Toast.makeText(activity, "Sound: " + (soundPlaying? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
            SoundManager.setSound();
        }
        else if(view.getId() == R.id.bkg_button1) {
            bkgId = 1;
            Toast.makeText(activity, "Background has been changed to GRASS", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId() == R.id.bkg_button2) {
            bkgId = 2;
            Toast.makeText(activity, "Background has been changed to SAND", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId() == R.id.bkg_button3) {
            bkgId = 3;
            Toast.makeText(activity, "Background has been changed to SNOW", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId() == R.id.bkg_button4) {
            bkgId = 4;
            Toast.makeText(activity, "Background has been changed to OCEAN", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId() == R.id.bkg_button5) {
            bkgId = 5;
            Toast.makeText(activity, "Background has been changed to CAVE", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId() == R.id.bkg_button6) {
            bkgId = 6;
            Toast.makeText(activity, "Background has been changed to CITY", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId() == R.id.bkg_button7) {
            bkgId = 7;
            Toast.makeText(activity, "Background has been changed to VOLCANO", Toast.LENGTH_SHORT).show();
        }
    }
}
