package edu.utsa.cs3443.theguidesgrandadventure.Controller;

import android.view.View;

import edu.utsa.cs3443.theguidesgrandadventure.CreditsActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

public class CreditsController implements View.OnClickListener {

    private CreditsActivity activity;

    public CreditsController(CreditsActivity activity) {
        this.activity = activity;
    }

    public void onClick(View view) {
        if(view.getId() == R.id.credit_return_button) {
            activity.finish();
        }
    }
}
