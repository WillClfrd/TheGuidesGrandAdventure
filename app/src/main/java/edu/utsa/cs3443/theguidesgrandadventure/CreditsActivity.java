package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.CreditsController;

/**
 * The CreditsActivity class creates a CreditsController and acts as the activity for the
 * credits view of the app, which holds all of the names and sites that were involved in making
 * this program.
 *
 * @author Jose Gracia
 * @version 1.0
 * @since 2023-04-20
 */
public class CreditsActivity extends AppCompatActivity {

    private CreditsController creditsController;

    /**
     * onCreate method for SettingsActivity that creates a controller, and a return button.
     *
     * @param savedInstanceState - Bundle of the settings class.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        creditsController = new CreditsController(this);

        Button returnMenuButton = findViewById(R.id.credit_return_button);
        setupButton(returnMenuButton);
    }

    /**
     * @param view This is a view item from the settings xml file, normally a button.
     */
    private void setupButton(View view){
        view.setOnClickListener(creditsController);
    }
}

