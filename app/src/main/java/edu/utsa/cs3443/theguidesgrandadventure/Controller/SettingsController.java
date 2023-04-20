package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import android.view.View;
import edu.utsa.cs3443.theguidesgrandadventure.R;
import edu.utsa.cs3443.theguidesgrandadventure.SettingsActivity;

//Settings Menu Controller
//Jose
public class SettingsController implements View.OnClickListener{

    private SettingsActivity activity;
    public static boolean musicOn = true; // For use later.

    public SettingsController(SettingsActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.return_button) { //Returns to In-game Menu.
            activity.finish();
        }
    }
}
