package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import android.content.Intent;
import android.view.View;

import edu.utsa.cs3443.theguidesgrandadventure.CreditsActivity;
import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.MainActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;
import edu.utsa.cs3443.theguidesgrandadventure.SettingsActivity;

//Main Menu controller
//Meagan
public class MainController implements View.OnClickListener{
    private final MainActivity activity;

    public MainController(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        Intent gameIntent;
        if(view.getId() == R.id.game_test_button){
            gameIntent = new Intent(activity, GameActivity.class);
            activity.startActivity(gameIntent);
        }
        else if(view.getId() == R.id.settings_button){
            gameIntent = new Intent(activity, SettingsActivity.class);
            activity.startActivity(gameIntent);
        }
        else if(view.getId() == R.id.credits_button){
            gameIntent = new Intent(activity, CreditsActivity.class);
            activity.startActivity(gameIntent);
        }
        else if(view.getId() == R.id.exit_button){
            activity.finishAffinity();
        }
    }
}

