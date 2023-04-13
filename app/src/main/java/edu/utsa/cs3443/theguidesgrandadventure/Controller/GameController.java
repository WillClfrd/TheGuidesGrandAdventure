package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.InGameMenuActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

//GameView controller
//William
public class GameController implements View.OnClickListener{
    private GameActivity activity;

    public GameController(GameActivity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.up_arrow){
            activity.getGameCanvas().getCharacter().setOrientation('u');
        }
        else if(view.getId() == R.id.down_arrow){
            activity.getGameCanvas().getCharacter().setOrientation('d');
        }
        else if(view.getId() == R.id.left_arrow){
            activity.getGameCanvas().getCharacter().setOrientation('l');
        }
        else if(view.getId() == R.id.right_arrow){
            activity.getGameCanvas().getCharacter().setOrientation('r');
        }
        else if(view.getId() == R.id.in_game_menu_button){
            Intent inGameMenuIntent = new Intent(activity, InGameMenuActivity.class);
            //pause game
            //open dialog for menu maybe
        }
    }
}
