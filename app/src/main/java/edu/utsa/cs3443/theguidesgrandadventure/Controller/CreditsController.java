package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import android.view.View;

import edu.utsa.cs3443.theguidesgrandadventure.CreditsActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

/**
 * The CreditsController class implements the OnClickListener and controls events for
 * CreditsActivity. The class also holds a class constructor and onClick for button interactions.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
public class CreditsController implements View.OnClickListener {

    private final CreditsActivity activity;

    /**
     * Class constructor that creates a CreditsController.
     *
     * @param activity - This is the activity of CreditsActivity class.
     */
    public CreditsController(CreditsActivity activity) {
        this.activity = activity;
    }

    /**
     * onClick method for CreditsController, it controls interactions for the
     * CreditsActivity class.
     *
     * @param view - This is a view item from the settings xml file.
     */
    public void onClick(View view) {
        if(view.getId() == R.id.credit_return_button) {
            activity.finish();
        }
    }
}
