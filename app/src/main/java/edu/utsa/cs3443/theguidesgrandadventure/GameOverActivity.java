package edu.utsa.cs3443.theguidesgrandadventure;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.GameOverController;
import edu.utsa.cs3443.theguidesgrandadventure.Model.GameCanvas;
import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;

/**
 * This class represents GameOverActivity objects.
 * Each GameOverActivity object is instantiated with a Bundle object.
 * GameOverActivity objects are responsible for initializing the activity_gameover layout.
 * The GameOverActivity class extends the AppCompatActivity class.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
public class GameOverActivity extends AppCompatActivity {
    private GameOverController controller;

    /**
     * This method is responsible for initializing the application view for the activity_gameover layout.
     *
     * @param savedInstanceState a Bundle class object that is passed into the inherited class onCreate method.
     */
    @SuppressLint("AppCompatMethod")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_gameover);

        String key = "score";
        int gameScore = this.getIntent().getIntExtra(key,0 );
        if(MediaPlayerManager.isPlaying) {
            MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance(this);
            if(gameScore == 0) {
                mediaPlayerManager.playMusic(R.raw.youstink);
            }
           else if(gameScore < 10) {
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

    /**
     * This method establishes an onClickListener for the view parameter View object.
     *
     * @param view the View that is assigned an OnClickListener
     */
    private void setupButton(View view){
        view.setOnClickListener(controller);
    }
}