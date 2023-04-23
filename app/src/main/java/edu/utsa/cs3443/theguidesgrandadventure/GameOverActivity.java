package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.GameOverController;
import edu.utsa.cs3443.theguidesgrandadventure.Model.GameCanvas;
import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;

//In game menu activity
//Anyone
public class GameOverActivity extends AppCompatActivity {
    private GameOverController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_gameover);

        if(MediaPlayerManager.isPlaying) {
            MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance(this);
            if(GameCanvas.getScoreGO() < 10) {
                mediaPlayerManager.playMusic(R.raw.gameover);
            }
            else {
                mediaPlayerManager.playMusic(R.raw.goodjob);
            }
        }

        controller = new GameOverController(this);

        Button mainMenuButton = findViewById(R.id.end_game_main_menu_button);
        Button newGameButton = findViewById(R.id.end_game_restart_game_button);
        Button leaderboardButton = findViewById(R.id.leaderboard_button);
        ImageView userInputEnter = findViewById(R.id.user_input_enter_button);
        Button backButton = findViewById(R.id.leaderboard_back_button);

        setupButton(mainMenuButton);
        setupButton(newGameButton);
        setupButton(leaderboardButton);
        setupButton(userInputEnter);
        setupButton(backButton);
    }

    public void setupButton(View view){
        view.setOnClickListener(controller);
    }
}