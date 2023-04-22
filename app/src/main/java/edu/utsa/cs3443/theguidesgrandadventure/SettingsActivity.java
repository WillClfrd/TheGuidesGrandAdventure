package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.SettingsController;

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
        ToggleButton musicToggleButton = findViewById(R.id.music_button);
        ToggleButton soundToggleButton = findViewById(R.id.sound_button);
        Button backgroundButton = findViewById(R.id.bkg_button1);
        Button backgroundButton2 = findViewById(R.id.bkg_button2);
        Button backgroundButton3 = findViewById(R.id.bkg_button3);
        Button backgroundButton4 = findViewById(R.id.bkg_button4);
        Button backgroundButton5 = findViewById(R.id.bkg_button5);
        Button backgroundButton6 = findViewById(R.id.bkg_button6);
        Button backgroundButton7 = findViewById(R.id.bkg_button7);
        Button returnMenuButton = findViewById(R.id.return_button);

        setupButton(musicToggleButton);
        setupButton(soundToggleButton);
        setupButton(backgroundButton);
        setupButton(backgroundButton2);
        setupButton(backgroundButton3);
        setupButton(backgroundButton4);
        setupButton(backgroundButton5);
        setupButton(backgroundButton6);
        setupButton(backgroundButton7);
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
