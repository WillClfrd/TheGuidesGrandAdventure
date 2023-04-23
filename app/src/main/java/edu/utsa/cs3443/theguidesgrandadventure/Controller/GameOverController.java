package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import java.io.IOException;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.GameOverActivity;
import edu.utsa.cs3443.theguidesgrandadventure.MainActivity;
import edu.utsa.cs3443.theguidesgrandadventure.Model.Leaderboard;
import edu.utsa.cs3443.theguidesgrandadventure.Model.UserRecord;
import edu.utsa.cs3443.theguidesgrandadventure.R;

public class GameOverController implements View.OnClickListener{
    private GameOverActivity activity;
    private AssetManager manager;
    private LinearLayout leaderboardLayout;
    private LinearLayout leaderboardList;
    private Button leaderboardBackButton;
    private LinearLayout highScoreLayout;
    EditText userInputBox;
    ImageView userInputEnter;
    private Intent intent;
    private Leaderboard scores;
    private String key = "score";
    private int charId = SettingsController.chrId;
    private String charName;
    private int score;
    private TextView scoreText;
    private TextView followerText;
    private int nameLengthLimit = 15;

    Handler handler = new Handler();

    @SuppressLint("SetTextI18n")
    public GameOverController(GameOverActivity activity){
        this.activity = activity;
        String key = "score";
        this.score = activity.getIntent().getIntExtra(key, 0);
        this.charName = findCharName(charId);
        this.scores = new Leaderboard();
        this.leaderboardLayout = activity.findViewById(R.id.leaderboard_layout);
        this.highScoreLayout = activity.findViewById(R.id.high_score_layout);
        this.userInputBox = activity.findViewById(R.id.user_input_box);
        this.userInputEnter = activity.findViewById(R.id.user_input_enter_button);
        this.leaderboardBackButton = activity.findViewById(R.id.leaderboard_back_button);
        this.leaderboardList = activity.findViewById(R.id.leaderboard_list_layout);
        manager = activity.getAssets();

        try {
            scores.loadLeaderboard(manager, activity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        charName = findCharName(charId);
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

        if(checkForHighScore() == -1 && scores.getScoresSize() < 5){
            addUsernamePopup();
        }
        else if(checkForHighScore() == -1){

        }
        else{
            addUsernamePopup();
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
        else if(view.getId() == R.id.leaderboard_button){
            addLeaderboardPopup();
        }
        else if(view.getId() == R.id.user_input_enter_button){
            if(userInputBox.getText().length() >= nameLengthLimit){
                Toast.makeText(activity, "Entered name is too long", LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(activity, "Added to leaderboard", LENGTH_SHORT).show();
                if(checkForHighScore() == 99){
                    scores.appendScoreRecord(new UserRecord(String.valueOf(userInputBox.getText()), score, charName, findCharacterImage(charName)));
                }
                else if(checkForHighScore() != -1) {
                    scores.insertScoreRecord(new UserRecord(String.valueOf(userInputBox.getText()), score, charName, findCharacterImage(charName)), checkForHighScore());
                }

                if(scores.getScoresSize() > 5){
                    scores.removeEndScore();
                }

                try {
                    scores.saveLeaderboard(activity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                removeUsernamePopup();
            }
        }
        else if(view.getId() == R.id.leaderboard_back_button){
            removeLeaderboardPopup();
        }
    }

    private String findCharName(int id){
        String name;

        switch(id){
            case 1:
                name = "Guide";
                break;
            case 2:
                name = "Rob";
                break;
            case 3:
                name = "Will";
                break;
            default:
                name = "Guide";
                break;
        }

        return name;
    }

    private Bitmap findCharacterImage(String charName){
        if(charName.equalsIgnoreCase("Rob")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.rob_left);
        }
        else if(charName.equalsIgnoreCase("Will")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.will_left);
        }
        else{
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.character_left);
        }
    }

    public int checkForHighScore(){
        int i;

        if(scores.getScoresSize() == 0){
            return 0;
        }
        for(i = 0; i < scores.getScoresSize(); ++i){
            if(score > scores.getRecord(i).getScore()){
                return i;
            }
        }
        if(scores.getScoresSize() < 5){
            return 99;
        }
        return -1;
    }

    private void addUsernamePopup(){
        highScoreLayout.setAlpha((float)1.0);
        userInputEnter.setClickable(true);
        userInputBox.setEnabled(true);
    }

    private void removeUsernamePopup(){
        highScoreLayout.setAlpha((float)0.0);
        userInputEnter.setClickable(false);
        userInputBox.setEnabled(false);
    }

    private void addLeaderboardPopup(){
        leaderboardLayout.setAlpha((float)1.0);
        leaderboardBackButton.setClickable(true);
        Typeface typeface = ResourcesCompat.getFont(activity, R.font.mainfont);
        int i;

        for(i = 0; i < scores.getScoresSize(); ++i){
            LinearLayout recordLayout = new LinearLayout(activity);
            recordLayout.setGravity(CENTER);
            ViewGroup.LayoutParams recordLayoutParams = new ViewGroup.LayoutParams(WRAP_CONTENT, 100);
            leaderboardList.addView(recordLayout, recordLayoutParams);
            leaderboardList.setGravity(CENTER);

            ImageView recordCharImage = new ImageView(activity);
            ViewGroup.LayoutParams charImageParams = new ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT);
            recordCharImage.setImageBitmap(scores.getRecord(i).getCharImage());
            recordLayout.addView(recordCharImage, charImageParams);

            TextView recordText = new TextView(activity);
            ViewGroup.LayoutParams recordTextParams = new ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT);
            String recordString = (i + 1) + ": " + scores.getRecord(i).getUserName() + " - " + scores.getRecord(i).getScore();
            recordText.setText(recordString);
            recordText.setTypeface(typeface);
            recordText.setTextSize((float)17.0);
            recordText.setTextColor(Color.argb(0xFF, 0xA6, 0xF2, 0xFF));//FFA6F2FF
            recordLayout.addView(recordText, recordTextParams);
        }
    }

    private void removeLeaderboardPopup(){
        leaderboardLayout.setAlpha((float)0.0);
        leaderboardList.removeAllViews();
        leaderboardBackButton.setClickable(false);
    }
}
