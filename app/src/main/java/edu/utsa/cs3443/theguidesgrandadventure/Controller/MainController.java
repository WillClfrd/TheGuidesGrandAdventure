package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.VERTICAL;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import edu.utsa.cs3443.theguidesgrandadventure.CreditsActivity;
import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.MainActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;
import edu.utsa.cs3443.theguidesgrandadventure.SettingsActivity;


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
        if(view.getId() == R.id.game_settings_button){
            gameIntent = new Intent(activity, CreditsActivity.class);
            activity.startActivity(gameIntent);
        }
    }
}

