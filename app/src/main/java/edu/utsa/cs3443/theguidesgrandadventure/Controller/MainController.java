package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import android.content.Intent;
import android.view.View;

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.MainActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

//Main Menu controller
//Meagan
public class MainController implements View.OnClickListener{
    private MainActivity activity;
    private Intent gameIntent;

    public MainController(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.game_test_button){
            gameIntent = new Intent(activity, GameActivity.class);
            activity.startActivity(gameIntent);
        }
    }
}

