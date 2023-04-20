package edu.utsa.cs3443.theguidesgrandadventure;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.CreditsController;

public class CreditsActivity extends AppCompatActivity {

    private CreditsController creditsController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        creditsController = new CreditsController(this);

        Button returnMenuButton = findViewById(R.id.credit_return_button);
        setupButton(returnMenuButton);
    }
    private void setupButton(View view){
        view.setOnClickListener(creditsController);
    }
}

