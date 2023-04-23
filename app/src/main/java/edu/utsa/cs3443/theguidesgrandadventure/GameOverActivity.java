package edu.utsa.cs3443.theguidesgrandadventure;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.GameOverController;
import edu.utsa.cs3443.theguidesgrandadventure.Model.GameCanvas;
import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;

//In game menu activity
//Anyone
public class GameOverActivity extends AppCompatActivity {
    private GameOverController controller;

    @SuppressLint("AppCompatMethod")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_gameover);

        if(MediaPlayerManager.isPlaying) {
            MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance(this);
            if(GameCanvas.getScoreGO() == 0) {
                mediaPlayerManager.playMusic(R.raw.youstink);
            }
           else if(GameCanvas.getScoreGO() < 10) {
                mediaPlayerManager.playMusic(R.raw.gameover);
            }
            else {
                mediaPlayerManager.playMusic(R.raw.goodjob);
            }
        }

        controller = new GameOverController(this);

        Button mainMenuButton = findViewById(R.id.end_game_main_menu_button);
        Button newGameButton = findViewById(R.id.end_game_restart_game_button);

        setupButton(mainMenuButton);
        setupButton(newGameButton);
    }

    private void setupButton(View view){
        view.setOnClickListener(controller);
    }
}