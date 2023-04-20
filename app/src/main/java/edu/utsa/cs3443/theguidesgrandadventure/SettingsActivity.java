package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.SettingsController;
import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;

//Settings menu activity
//Jose - testing my local repo again
public class SettingsActivity extends AppCompatActivity {
    private SettingsController settingsController;
    private MediaPlayerManager mediaPlayerManager;
    private ToggleButton musicToggleButton;
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

    private void setupButton(View view){
        view.setOnClickListener(settingsController);
    }
}
