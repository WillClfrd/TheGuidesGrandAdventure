package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.VERTICAL;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.InGameMenuActivity;
import edu.utsa.cs3443.theguidesgrandadventure.MainActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;
import edu.utsa.cs3443.theguidesgrandadventure.SettingsActivity;

//GameView controller
//William
public class GameController implements View.OnClickListener{
    private GameActivity activity;
    private FrameLayout gameLayout;
    private LinearLayout inGameMenu;
    private boolean isInGameMenuUp;
    private int inGameMenuId = 99;
    private int menuHeaderId = 1;
    private int mainMenuId = 2;
    private int settingsId = 3;
    private int newGameId = 4;
    private int returnId = 5;

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

        inGameMenu = new LinearLayout(this.activity);
        inGameMenu.setOrientation(VERTICAL);
        inGameMenu.setGravity(CENTER);
        inGameMenu.setBackgroundColor(Color.BLACK);
        inGameMenu.setAlpha((float)0.9);
//        inGameMenu.setId(this.inGameMenuId);
        this.gameLayout.addView(inGameMenu, menuParams);

        TextView menuHeader = new TextView(this.activity);
        inGameMenu.setId(this.menuHeaderId);
        menuHeader.setTextSize((float)45.0);
        menuHeader.setTextColor(Color.WHITE);
        menuHeader.setPadding(0,0,0,40);
        menuHeader.setText("Paused");
        inGameMenu.addView(menuHeader, headerParams);

        Button mainMenuButton = new Button(this.activity);
        mainMenuButton.setId(this.mainMenuId);
        mainMenuButton.setTextColor(Color.WHITE);
        mainMenuButton.setText("Main Menu");
        inGameMenu.addView(mainMenuButton, buttonParams);
        mainMenuButton.setOnClickListener(this);

        Button settingsButton = new Button(this.activity);
        settingsButton.setId(this.settingsId);
        settingsButton.setTextColor(Color.WHITE);
        settingsButton.setText("Settings");
        inGameMenu.addView(settingsButton, buttonParams);
        settingsButton.setOnClickListener(this);

        Button newGameButton = new Button(this.activity);
        newGameButton.setId(this.newGameId);
        newGameButton.setTextColor(Color.WHITE);
        newGameButton.setText("New Game");
        inGameMenu.addView(newGameButton, buttonParams);
        newGameButton.setOnClickListener(this);

        Button returnToGameButton = new Button(this.activity);
        returnToGameButton.setId(this.returnId);
        returnToGameButton.setTextColor(Color.WHITE);
        returnToGameButton.setText("Return to Game");
        inGameMenu.addView(returnToGameButton, buttonParams);
        returnToGameButton.setOnClickListener(this);
    }

    private void removeMenuLayout(){
        inGameMenu.removeAllViews();
        this.gameLayout.removeView(this.inGameMenu);
    }
}
