package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import android.content.Intent;
import android.view.View;

import edu.utsa.cs3443.theguidesgrandadventure.CreditsActivity;
import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.MainActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;
import edu.utsa.cs3443.theguidesgrandadventure.SettingsActivity;

/**
 * The MainController class implements the OnClickListener and controls events for
 * MainActivity. The class also holds a class constructor and onClick for button interactions.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * @version 1.4.15
 * @since 2023-04-24
 */
public class MainController implements View.OnClickListener{
    private final MainActivity activity;

    /**
     * Class constructor that creates a MainController.
     *
     * @param activity - This is the activity of MainActivity class.
     */
    public MainController(MainActivity activity){
        this.activity = activity;
    }

    /**
     * onClick method for MainController, it controls interactions for the
     * MainActivity class.
     *
     * @param view - This is a view item from the settings xml file.
     */
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

