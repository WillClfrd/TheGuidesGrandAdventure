package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.SettingsController;

//Settings menu activity
//Jose - testing my local repo again
public class SettingsActivity extends AppCompatActivity {

    private SettingsController settingsController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsController = new SettingsController(this);

        Button returnMenuButton = findViewById(R.id.return_button);
        setupButton(returnMenuButton);
    }

    private void setupButton(View view){
        view.setOnClickListener(settingsController);
    }
}

