package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.GameOverActivity;
import edu.utsa.cs3443.theguidesgrandadventure.MainActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

public class GameOverController implements View.OnClickListener{
    private final GameOverActivity activity;
    private final int score;
    private final TextView scoreText;
    private final TextView followerText;

    Handler handler = new Handler();

    @SuppressLint("SetTextI18n")
    public GameOverController(GameOverActivity activity){
        this.activity = activity;
        String key = "score";
        this.score = activity.getIntent().getIntExtra(key, 0);
        scoreText = activity.findViewById(R.id.score_textview);
        scoreText.setText("Score: " + score);

        followerText = activity.findViewById(R.id.follower_textview);
        System.out.println("testing");

        if(score == 0) {
            followerText.setText("How did you even manage this???");
        }
        else if(score == 5) {
            followerText.setText("You Found " + (score / 5) + " Lost Tour Member!");
        }
        else if(score == 10) {
            followerText.setText("You Found " + (score / 5) + " Lost Tour Members!");
        }
        else {
            followerText.setText("You Found " + (score / 5) + " Lost Tour Members!");
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(score >= 10) {
                    int color = Color.rgb(
                            (int) (Math.random() * 255),
                            (int) (Math.random() * 255),
                            (int) (Math.random() * 255)
                    );
                    scoreText.setTextColor(color);
                    followerText.setTextColor(color);
                    handler.postDelayed(this, 250);
                }
            }
        };

        handler.post(runnable);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if(view.getId() == R.id.end_game_main_menu_button){
            intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
        else if(view.getId() == R.id.end_game_restart_game_button){
            intent = new Intent(activity, GameActivity.class);
            activity.startActivity(intent);
        }
    }
}
