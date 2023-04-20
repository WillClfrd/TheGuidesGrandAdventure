package edu.utsa.cs3443.theguidesgrandadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.MainController;
import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;

//Main Menu activity
//Meagan
public class MainActivity extends AppCompatActivity {
    private MainController controller;
    private MediaPlayerManager mediaPlayerManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this);
        mediaPlayerManager = MediaPlayerManager.getInstance(this);
        mediaPlayerManager.playMusic(R.raw.mainmenu);

        Button gameTestButton = findViewById(R.id.game_test_button);
        Button settingsButton = findViewById(R.id.settings_button);
        Button creditsButton = findViewById(R.id.credits_button);
        Button exitButton = findViewById(R.id.exit_button);
        setupButton(gameTestButton);
        setupButton(settingsButton);
        setupButton(creditsButton);
        setupButton(exitButton);
    }

    private void setupButton(View view){
        view.setOnClickListener(controller);
    }
}