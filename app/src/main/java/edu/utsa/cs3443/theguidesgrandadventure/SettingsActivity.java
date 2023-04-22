package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.SettingsController;
import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;
import edu.utsa.cs3443.theguidesgrandadventure.Model.SoundManager;

/**
 * The SettingsActivity class creates a SettingsController and acts as the activity for the
 * settings view of the app.
 *
 * @author Jose Gracia
 * @version 1.0
 * @since 2023-04-20
 */
public class SettingsActivity extends AppCompatActivity {
    private SettingsController settingsController;
    private MediaPlayerManager mediaPlayerManager;
    private ToggleButton musicToggleButton;
    private ToggleButton soundToggleButton;
    private Button backgroundButton;
    private Button returnMenuButton;

    /**
     * onCreate method for SettingsActivity that creates a controller, mediaPlayer, and buttons on
     * startup for use by the program.
     *
     * @param savedInstanceState - Bundle of the settings class.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsController = new SettingsController(this);
        musicToggleButton = findViewById(R.id.music_button);
        soundToggleButton = findViewById(R.id.sound_button);
        backgroundButton = findViewById(R.id.bkg_button);
        returnMenuButton = findViewById(R.id.return_button);

        setupButton(musicToggleButton);
        setupButton(soundToggleButton);
        setupButton(backgroundButton);
        setupButton(returnMenuButton);
    }

    /**
     *
     * @param view This is a view item from the settings xml file, normally a button.
     */
    private void setupButton(View view){
        view.setOnClickListener(settingsController);
    }
}
