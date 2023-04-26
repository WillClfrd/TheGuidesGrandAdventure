package edu.utsa.cs3443.theguidesgrandadventure;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.GameController;
import edu.utsa.cs3443.theguidesgrandadventure.Model.CollectibleThread;
import edu.utsa.cs3443.theguidesgrandadventure.Model.GameCanvas;
import edu.utsa.cs3443.theguidesgrandadventure.Model.CharacterThread;
import edu.utsa.cs3443.theguidesgrandadventure.Model.MediaPlayerManager;

/**
 * This class represents GameActivity objects.
 * Each GameActivity object is instantiated with a Bundle object.
 * GameActivity objects are responsible for initializing the activity_game layout.
 * The GameActivity class extends the AppCompatActivity class.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
public class GameActivity extends AppCompatActivity {
    private GameCanvas gameCanvas;
    private GameController controller;
    private CharacterThread characterThread;
    private CollectibleThread collectibleThread;

    /**
     * This method is responsible for initializing the application view for the activity_game layout.
     *
     * @param savedInstanceState a Bundle class object that is passed into the inherited class onCreate method.
     */
    @SuppressLint("AppCompatMethod")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_game);

        LinearLayout ll = findViewById(R.id.game_view_ll);
        gameCanvas = new GameCanvas(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        ll.addView(gameCanvas, lp);

        controller = new GameController(this);

        if(MediaPlayerManager.isPlaying) {
            MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance(this);
            mediaPlayerManager.playMusic(R.raw.gamemusic);
        }

        ImageButton upButton = findViewById(R.id.up_arrow);
        ImageButton downButton = findViewById(R.id.down_arrow);
        ImageButton rightButton = findViewById(R.id.right_arrow);
        ImageButton leftButton = findViewById(R.id.left_arrow);
        ImageButton inGameMenuButton = findViewById(R.id.in_game_menu_button);

        setupButton(upButton);
        setupButton(downButton);
        setupButton(rightButton);
        setupButton(leftButton);
        setupButton(inGameMenuButton);

        collectibleThread = new CollectibleThread(this);
        collectibleThread.setRunning(true);
        collectibleThread.start();

        characterThread = new CharacterThread(this);
        characterThread.setRunning(true);
        characterThread.start();
    }

    /**
     * This method establishes an onClickListener for the view parameter View object.
     *
     * @param view the View that is assigned an OnClickListener
     */
    private void setupButton(View view){
        view.setOnClickListener(controller);
    }

    /**
     * Returns the GameActivity gameCanvas attribute.
     *
     * @return the GameActivity gameCanvas attribute.
     */
    public GameCanvas getGameCanvas(){
        return this.gameCanvas;
    }

    /**
     * Returns the GameActivity characterThread attribute.
     *
     * @return the GameActivity characterThread attribute.
     */
    public CharacterThread getCharacterThread(){
        return this.characterThread;
    }

    /**
     * Returns the GameActivity collectibleThread attribute.
     *
     * @return the GameActivity collectibleThread attribute.
     */
    public CollectibleThread getCollectibleThread(){
        return this.collectibleThread;
    }
}
