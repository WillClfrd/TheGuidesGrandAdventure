package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.MainController;
import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;

/**
 * The MainActivity class creates a MainController and acts as the activity for the
 * main view of the app.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * @version 1.4.15
 * @since 2023-04-24
 */
public class MainActivity extends AppCompatActivity {
    private MainController controller;
    MediaPlayerManager mediaPlayerManager;
    double counter = 0;
    boolean countingUp = true;
    Handler handler = new Handler();

    /**
     * onCreate method for MainActivity that creates a controller, mediaPlayer, and buttons on
     * startup for use by the program.
     *
     * @param savedInstanceState - Bundle of the settings class.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new MainController(this);

        // MediaPlayer toggle check.
        if(MediaPlayerManager.isPlaying) {
            mediaPlayerManager = MediaPlayerManager.getInstance(this);
            mediaPlayerManager.playMusic(R.raw.mainmenu);
        }

        // View object setups.
        ImageView titleImage = findViewById(R.id.title_logo);
        Button gameTestButton = findViewById(R.id.game_test_button);
        Button settingsButton = findViewById(R.id.settings_button);
        Button creditsButton = findViewById(R.id.credits_button);
        Button exitButton = findViewById(R.id.exit_button);
        setupButton(gameTestButton);
        setupButton(settingsButton);
        setupButton(creditsButton);
        setupButton(exitButton);

        //Runnable that moves title image (creates sway animation)
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                    if(countingUp) {
                        counter = counter + 0.25;
                        titleImage.setRotationY((float) counter - 10);
                        if (counter == 20) {
                            countingUp = false;
                        }
                    }
                    else {
                        counter = counter - 0.25;
                        titleImage.setRotationY((float) counter - 10);
                            if (counter == 0) {
                                countingUp = true;
                            }
                        }
                        handler.postDelayed(this, 100);
                    }
                };
                handler.post(runnable);
            }

    /**
     * onDestroy method that releases the music player upon exiting the app.
     */
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayerManager.releasePlayer(); // Releases media.
    }

    /**
     * @param view This is a view item from the settings xml file, normally a button.
     */
    private void setupButton(View view){
        view.setOnClickListener(controller);
    }
}