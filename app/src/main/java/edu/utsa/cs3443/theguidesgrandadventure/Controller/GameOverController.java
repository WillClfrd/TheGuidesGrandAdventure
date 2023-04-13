package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.GameOverActivity;
import edu.utsa.cs3443.theguidesgrandadventure.MainActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

public class GameOverController implements View.OnClickListener{
    private GameOverActivity activity;
    private Intent intent;
    private String key = "score";
    private int score;
    private TextView scoreText;
    private TextView followerText;


    public GameOverController(GameOverActivity activity){
        this.activity = activity;

        this.score = activity.getIntent().getIntExtra(key, 0);

        scoreText = activity.findViewById(R.id.score_textview);
        scoreText.setText("Score: " + score);

        followerText = activity.findViewById(R.id.follower_textview);
        followerText.setText("You Found " + (score / 5) + " Lost Tour Members!");
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.end_game_main_menu_button){
            intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
        }
        else if(view.getId() == R.id.end_game_restart_game_button){
            intent = new Intent(activity, GameActivity.class);
            activity.startActivity(intent);
        }
    }
}
