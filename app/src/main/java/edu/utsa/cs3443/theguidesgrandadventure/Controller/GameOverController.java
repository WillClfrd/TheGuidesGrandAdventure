package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.LEFT;
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

/**
 * This class represents GameOverController objects.
 * The GameOverController class maintains the activity, leaderboardLayout, leaderboardList, leaderboardBackButton, highScoreLayout, userInputBox, userInputEnter, scores, charName, score, scoreText, followerText, and handler attributes as well as handling click events for the GameOverActivity class.
 * The GameOverController class implements the View.OnClickListener interface.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
public class GameOverController implements View.OnClickListener{
    private final GameOverActivity activity;
    private final LinearLayout leaderboardLayout;
    private final LinearLayout leaderboardList;
    private final Button leaderboardBackButton;
    private final LinearLayout highScoreLayout;
    private final EditText userInputBox;
    private final ImageView userInputEnter;
    private final Leaderboard scores;
    private String charName;
    private final int score;
    private final TextView scoreText;
    private final TextView followerText;
    private final Handler handler = new Handler();

    /**
     * Creates GameOverController objects.
     * Initializes the activity, score, charName, scores, leaderboardLayout, highScoreLayout, userInputBox, userInputEnter, leaderboardBackButton, leaderboardList attributes.
     *
     * @param activity the GameOverActivity to be assigned to the activity attribute.
     */
    @SuppressLint("SetTextI18n")
    public GameOverController(GameOverActivity activity){
        this.activity = activity;
        String key = "score";
        this.score = activity.getIntent().getIntExtra(key, 0);
        int charId = SettingsController.chrId;
        this.charName = findCharName(charId);
        this.scores = new Leaderboard();
        this.leaderboardLayout = activity.findViewById(R.id.leaderboard_layout);
        this.highScoreLayout = activity.findViewById(R.id.high_score_layout);
        this.userInputBox = activity.findViewById(R.id.user_input_box);
        this.userInputEnter = activity.findViewById(R.id.user_input_enter_button);
        this.leaderboardBackButton = activity.findViewById(R.id.leaderboard_back_button);
        this.leaderboardList = activity.findViewById(R.id.leaderboard_list_layout);
        AssetManager manager = activity.getAssets();

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
        //else if(checkForHighScore() == -1){
        //}
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

    /**
     * Maintains onClickListeners for views of the GameOverController activity attribute which have been assigned onClickListeners.
     *
     * @param view the View object passed as a parameter from the detected click event.
     */
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
            int nameLengthLimit = 15;
            if(userInputBox.getText().length() >= nameLengthLimit){
                Toast.makeText(activity, "Entered name is too long!", LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(activity, "Added to leaderboard!", LENGTH_SHORT).show();
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

    /**
     * Used to return a String corresponding to the given id parameter.
     *
     * @param id the value used to determine the correct String to return.
     * @return the String corresponding to the given id.
     */
    private String findCharName(int id){
        String name;

        switch(id){
            case 2:
                name = "Rob";
                break;
            case 3:
                name = "Will";
                break;
            case 4:
                name = "Meagan";
                break;
            case 5:
                name = "Iker";
                break;
            case 6:
                name = "Joao";
                break;
            case 7:
                name = "Addy";
                break;
            case 8:
                name = "Trent";
                break;
            case 9:
                name = "Serena";
                break;
            default:
                name = "Guide";
                break;
        }

        return name;
    }

    /**
     * Used to return a Bitmap corresponding to the given charName parameter.
     *
     * @param charName the value used to determine the correct Bitmap to return.
     * @return the Bitmap corresponding to the given charName.
     */
    private Bitmap findCharacterImage(String charName){
        if(charName.equalsIgnoreCase("Rob")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.rob_left);
        }
        else if(charName.equalsIgnoreCase("Will")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.will_left);
        }
        else if(charName.equalsIgnoreCase("Meagan")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.meagan_left);
        }
        else if(charName.equalsIgnoreCase("Iker")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.gman_left);
        }
        else if(charName.equalsIgnoreCase("Joao")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.joao_left);
        }
        else if(charName.equalsIgnoreCase("Addy")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.addy_left);
        }
        else if(charName.equalsIgnoreCase("Trent")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.trent_left);
        }
        else if(charName.equalsIgnoreCase("Serena")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.serena_left);
        }
        else{
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.character_left);
        }
    }

    /**
     * Iterates through records contained in the Leaderboard.scores ArrayList in order to compare the UserRecord.score attribute against the GameOveActivity.score attribute in order to determine if a new UserRecord should be added to the Leaderboard.scores ArrayList.
     *
     * @return an integer, 0 if the Leaderboard.scores ArrayList is of size 0, the index where a new record should be inserted if a new record should be inserted, 99 if the Leaderboard.score ArrayList is smaller than size 5, but larger than 0, otherwise -1.
     */
    public int checkForHighScore() {
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

    /**
     * Used to make views related to the GameOverActivity userNamePopup visible and interactable.
     */
    private void addUsernamePopup(){
        highScoreLayout.setAlpha((float)1.0);
        userInputEnter.setClickable(true);
        userInputBox.setEnabled(true);
    }

    /**
     * Used to make views related to the GameOverActivity userNamePopup non-visible and non-interactable.
     */
    private void removeUsernamePopup(){
        highScoreLayout.setAlpha((float)0.0);
        userInputEnter.setClickable(false);
        userInputBox.setEnabled(false);
    }

    /**
     * Used to make views related to the GameOverActivity leaderboardPopup visible and interactable.
     */
    @SuppressLint("RtlHardcoded")
    private void addLeaderboardPopup(){
        leaderboardLayout.setAlpha((float)1.0);
        leaderboardBackButton.setClickable(true);
        Typeface typeface = ResourcesCompat.getFont(activity, R.font.mainfont);
        Typeface subTypeface = ResourcesCompat.getFont(activity, R.font.retro_computer_personal_use);
        int i;

        for(i = 0; i < scores.getScoresSize(); ++i){
            LinearLayout recordLayout = new LinearLayout(activity);
            recordLayout.setGravity(CENTER);
            ViewGroup.LayoutParams recordLayoutParams = new ViewGroup.LayoutParams(WRAP_CONTENT, 125);
            leaderboardList.addView(recordLayout, recordLayoutParams);
            leaderboardList.setGravity(CENTER);

            ImageView recordCharImage = new ImageView(activity);
            ViewGroup.LayoutParams charImageParams = new ViewGroup.LayoutParams(100, MATCH_PARENT);
            recordCharImage.setForegroundGravity(CENTER);
            recordCharImage.setImageBitmap(scores.getRecord(i).getCharImage());
            recordLayout.addView(recordCharImage, charImageParams);

            TextView placeText = new TextView(activity);
            ViewGroup.LayoutParams placeTextParams = new ViewGroup.LayoutParams(175, MATCH_PARENT);
            String placeString = (i + 1) + ": ";
            placeText.setText(placeString);
            placeText.setGravity(CENTER);
            placeText.setTypeface(typeface);
            placeText.setTextSize((float)17.0);
            placeText.setTextColor(Color.argb(0xFF, 0x83, 0xB6, 0xFF));//FFA6F2FF
            recordLayout.addView(placeText, placeTextParams);

            TextView recordText = new TextView(activity);
            ViewGroup.LayoutParams recordTextParams = new ViewGroup.LayoutParams(500, MATCH_PARENT);
            String recordString = scores.getRecord(i).getUserName() + "\t\t" + scores.getRecord(i).getScore();
            recordText.setText(recordString);
            recordText.setGravity(CENTER | LEFT);
            recordText.setTypeface(subTypeface);
            recordText.setTextSize((float)17.0);
            recordText.setTextColor(Color.argb(0xFF, 0x83, 0xB6, 0xFF));//FFA6F2FF
            recordLayout.addView(recordText, recordTextParams);
        }
    }

    /**
     * Used to make views related to the GameOverActivity leaderboardPopup non-visible and non-interactable.
     */
    private void removeLeaderboardPopup(){
        leaderboardLayout.setAlpha((float)0.0);
        leaderboardList.removeAllViews();
        leaderboardBackButton.setClickable(false);
    }
}
