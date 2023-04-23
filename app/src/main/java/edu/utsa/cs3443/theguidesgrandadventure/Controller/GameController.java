package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.VERTICAL;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.MainActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;
import edu.utsa.cs3443.theguidesgrandadventure.SettingsActivity;

//GameView controller
//William
@SuppressWarnings("ALL")
public class GameController implements View.OnClickListener{
    private final GameActivity activity;
    private FrameLayout gameLayout;
    private LinearLayout inGameMenu;
    private boolean isInGameMenuUp;
    private final int mainMenuId = 2;
    private final int settingsId = 3;
    private final int newGameId = 4;
    private final int returnId = 5;

    public GameController(GameActivity activity){
        this.activity = activity;
        this.isInGameMenuUp = false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.up_arrow){
            this.activity.getGameCanvas().getCharacter().setOrientation('u');
        }
        else if(view.getId() == R.id.down_arrow){
            this.activity.getGameCanvas().getCharacter().setOrientation('d');
        }
        else if(view.getId() == R.id.left_arrow){
            this.activity.getGameCanvas().getCharacter().setOrientation('l');
            this.activity.getGameCanvas().getCharacter().setCharImage(this.activity.getGameCanvas().getCharacter().getCharImageLeft());
        }
        else if(view.getId() == R.id.right_arrow){
            this.activity.getGameCanvas().getCharacter().setOrientation('r');
            this.activity.getGameCanvas().getCharacter().setCharImage(this.activity.getGameCanvas().getCharacter().getCharImageRight());
        }
        else if(view.getId() == R.id.in_game_menu_button){
            if(!(this.isInGameMenuUp)) {
                this.activity.getCharacterThread().setIsPaused(true);
                this.activity.getCollectibleThread().setIsPaused(true);
                createMenuLayout();
                this.isInGameMenuUp = true;
            }
        }
        else if(view.getId() == this.mainMenuId){
            Intent mainMenuIntent = new Intent(this.activity, MainActivity.class);
            this.activity.startActivity(mainMenuIntent);
        }
        else if(view.getId() == this.settingsId){
            Intent settingsIntent = new Intent(this.activity, SettingsActivity.class);
            this.activity.startActivity(settingsIntent);
        }
        else if(view.getId() == this.newGameId){
            Intent gameIntent = new Intent(this.activity, GameActivity.class);
            this.activity.startActivity(gameIntent);
        }
        else if(view.getId() == this.returnId){
            removeMenuLayout();

            this.activity.getCharacterThread().setIsPaused(false);
            this.activity.getCollectibleThread().setIsPaused(false);
            this.isInGameMenuUp = false;
        }
    }

    private void createMenuLayout(){
        ViewGroup.LayoutParams menuParams = new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        ViewGroup.LayoutParams headerParams = new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        ViewGroup.LayoutParams buttonParams = new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

        this.gameLayout = this.activity.findViewById(R.id.game_frame_layout);

        Typeface headT = ResourcesCompat.getFont(activity, R.font.mainfont);
        Typeface mainT = ResourcesCompat.getFont(activity, R.font.retro_computer_personal_use);
        int lBlue = activity.getResources().getColor(R.color.light_blue);
        int nBlue = activity.getResources().getColor(R.color.navy);

        inGameMenu = new LinearLayout(this.activity);
        inGameMenu.setOrientation(VERTICAL);
        inGameMenu.setGravity(CENTER);
        inGameMenu.setBackgroundColor(nBlue);
        inGameMenu.setAlpha((float)0.9);
        this.gameLayout.addView(inGameMenu, menuParams);

        TextView menuHeader = new TextView(this.activity);
        int menuHeaderId = 1;
        inGameMenu.setId(menuHeaderId);
        menuHeader.setTypeface(headT);
        menuHeader.setTextSize((float)30.0);
        menuHeader.setTextColor(lBlue);
        menuHeader.setPadding(0,0,0,40);
        menuHeader.setText("GAME PAUSED");
        inGameMenu.addView(menuHeader, headerParams);

        Button mainMenuButton = new Button(this.activity);
        mainMenuButton.setWidth(600);
        mainMenuButton.setId(this.mainMenuId);
        mainMenuButton.setTypeface(mainT);
        mainMenuButton.setTextSize((float)20.0);
        mainMenuButton.setTextColor(lBlue);
        mainMenuButton.getBackground().setColorFilter(ContextCompat.getColor(activity, R.color.blue_200), PorterDuff.Mode.SRC);
        mainMenuButton.setText("Main Menu");
        inGameMenu.addView(mainMenuButton, buttonParams);
        mainMenuButton.setOnClickListener(this);

        Button settingsButton = new Button(this.activity);
        settingsButton.setWidth(600);
        settingsButton.setId(this.settingsId);
        settingsButton.setTypeface(mainT);
        settingsButton.setTextSize((float)20.0);
        settingsButton.setTextColor(lBlue);
        settingsButton.getBackground().setColorFilter(ContextCompat.getColor(activity, R.color.blue_200), PorterDuff.Mode.SRC);
        settingsButton.setText("Settings");
        inGameMenu.addView(settingsButton, buttonParams);
        settingsButton.setOnClickListener(this);

        Button newGameButton = new Button(this.activity);
        newGameButton.setWidth(600);
        newGameButton.setId(this.newGameId);
        newGameButton.setTypeface(mainT);
        newGameButton.setTextSize((float)20.0);
        newGameButton.setTextColor(lBlue);
        newGameButton.getBackground().setColorFilter(ContextCompat.getColor(activity, R.color.blue_200), PorterDuff.Mode.SRC);
        newGameButton.setText("New Game");
        inGameMenu.addView(newGameButton, buttonParams);
        newGameButton.setOnClickListener(this);

        Button returnToGameButton = new Button(this.activity);
        returnToGameButton.setWidth(600);
        returnToGameButton.setId(this.returnId);
        returnToGameButton.setTypeface(mainT);
        returnToGameButton.setTextSize((float)20.0);
        returnToGameButton.setTextColor(lBlue);
        returnToGameButton.getBackground().setColorFilter(ContextCompat.getColor(activity, R.color.blue_200), PorterDuff.Mode.SRC);
        returnToGameButton.setText("Resume");
        inGameMenu.addView(returnToGameButton, buttonParams);
        returnToGameButton.setOnClickListener(this);
    }

    private void removeMenuLayout(){
        inGameMenu.removeAllViews();
        this.gameLayout.removeView(this.inGameMenu);
    }
}
