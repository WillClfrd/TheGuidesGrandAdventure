package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
    Handler handler = new Handler();

    boolean countingUp = true;

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
        ImageView will = findViewById(R.id.dev_image1);
        ImageView rob = findViewById(R.id.dev_image2);
        ImageView meag = findViewById(R.id.dev_image3);
        setupButton(returnMenuButton);

        int willLeft = R.drawable.will_left;
        int willRight = R.drawable.will_right;
        int robLeft = R.drawable.rob_left;
        int robRight = R.drawable.rob_right;
        int meagLeft = R.drawable.meagan_left;
        int meagRight = R.drawable.meagan_right;

        // Character walking runnable.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if(countingUp) {
                    will.setImageResource(willLeft);
                    rob.setImageResource(robLeft);
                    meag.setImageResource(meagLeft);
                    countingUp = false;
                }
                else {
                    will.setImageResource(willRight);
                    rob.setImageResource(robRight);
                    meag.setImageResource(meagRight);
                    countingUp = true;
                }
                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);
    }

    /**
     * @param view This is a view item from the settings xml file, normally a button.
     */
    private void setupButton(View view){
        view.setOnClickListener(creditsController);
    }
}

