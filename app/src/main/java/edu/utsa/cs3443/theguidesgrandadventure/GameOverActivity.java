package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.GameOverController;
import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;

//In game menu activity
//Anyone
public class GameOverActivity extends AppCompatActivity {
    private GameOverController controller;
    private MediaPlayerManager mediaPlayerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_gameover);

        mediaPlayerManager = MediaPlayerManager.getInstance(this);
        mediaPlayerManager.playMusic(R.raw.gameover);

        controller = new GameOverController(this);

        Button mainMenuButton = findViewById(R.id.end_game_main_menu_button);
        Button newGameButton = findViewById(R.id.end_game_restart_game_button);

        setupButton(mainMenuButton);
        setupButton(newGameButton);
    }

    protected void onPause() {
        super.onPause();

        // Pause the music playback and release the media player instance
        mediaPlayerManager.pauseMusic();
        mediaPlayerManager.releaseMediaPlayer();
    }
    private void setupButton(View view){
        view.setOnClickListener(controller);
    }
}