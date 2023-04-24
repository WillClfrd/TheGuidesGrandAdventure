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
 * @version 1.4.15
 * @since 2023-04-24
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

        // New SettingsController for this activity.
        settingsController = new SettingsController(this);

        // Initializes music and sound toggle buttons.
        ToggleButton musicToggleButton = findViewById(R.id.music_button);
        ToggleButton soundToggleButton = findViewById(R.id.sound_button);

        // Initializes character choice buttons.
        Button characterButton = findViewById(R.id.chr_button1);
        Button characterButton2 = findViewById(R.id.chr_button2);
        Button characterButton3 = findViewById(R.id.chr_button3);
        Button characterButton4 = findViewById(R.id.chr_button4);
        Button characterButton5 = findViewById(R.id.chr_button5);
        Button characterButton6 = findViewById(R.id.chr_button6);
        Button characterButton7 = findViewById(R.id.chr_button7);
        Button characterButton8 = findViewById(R.id.chr_button8);
        Button characterButton9 = findViewById(R.id.chr_button9);

        // Initializes background choice buttons.
        Button backgroundButton = findViewById(R.id.bkg_button1);
        Button backgroundButton2 = findViewById(R.id.bkg_button2);
        Button backgroundButton3 = findViewById(R.id.bkg_button3);
        Button backgroundButton4 = findViewById(R.id.bkg_button4);
        Button backgroundButton5 = findViewById(R.id.bkg_button5);
        Button backgroundButton6 = findViewById(R.id.bkg_button6);
        Button backgroundButton7 = findViewById(R.id.bkg_button7);
        Button returnMenuButton = findViewById(R.id.return_button);

        // Sets toggle buttons for music and sounds on settings view.
        setupButton(musicToggleButton);
        setupButton(soundToggleButton);

        // Sets up character choice buttons on settings view.
        setupButton(characterButton);
        setupButton(characterButton2);
        setupButton(characterButton3);
        setupButton(characterButton4);
        setupButton(characterButton5);
        setupButton(characterButton6);
        setupButton(characterButton7);
        setupButton(characterButton8);
        setupButton(characterButton9);

        // Sets up background choice buttons on settings view.
        setupButton(backgroundButton);
        setupButton(backgroundButton2);
        setupButton(backgroundButton3);
        setupButton(backgroundButton4);
        setupButton(backgroundButton5);
        setupButton(backgroundButton6);
        setupButton(backgroundButton7);

        // Sets up return button on settings view.
        setupButton(returnMenuButton);
    }

    /**
     * @param view This is a view item from the settings xml file, normally a button.
     */
    private void setupButton(View view){
        view.setOnClickListener(settingsController);
    }
}
