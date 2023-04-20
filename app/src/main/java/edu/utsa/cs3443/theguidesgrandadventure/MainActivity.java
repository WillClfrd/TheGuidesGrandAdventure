package edu.utsa.cs3443.theguidesgrandadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.MainController;

//Main Menu activity
//Meagan
public class MainActivity extends AppCompatActivity {
    private MainController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this);

        Button gameTestButton = findViewById(R.id.game_test_button);
        setupButton(gameTestButton);
    }

    private void setupButton(View view){
        view.setOnClickListener(controller);
    }
}