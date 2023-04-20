package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.SettingsController;
import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;

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

    /**
     * onCreate method for SettingsActivity that creates a controller, mediaPlayer, and buttons on
     * startup for use by the program.
     *
     * @param savedInstanceState - Instance state of the settings class.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsController = new SettingsController(this);

        mediaPlayerManager = MediaPlayerManager.getInstance(this);

        musicToggleButton = findViewById(R.id.music_button);
        musicToggleButton.setOnClickListener(v -> {
            mediaPlayerManager.toggleSound();
            musicToggleButton.setChecked(mediaPlayerManager.isPlaying());
        });

        ToggleButton soundToggleButton = findViewById(R.id.sound_button);
        setupButton(soundToggleButton);

        Button bkgButton = findViewById(R.id.bkg_button);
        setupButton(bkgButton);

        Button returnMenuButton = findViewById(R.id.return_button);
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
