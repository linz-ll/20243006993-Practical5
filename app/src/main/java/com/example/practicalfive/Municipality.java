package com.example.practicalfive;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import android.widget.Toast;

public class Municipality extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY =
            "municipality_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_municipality);
        showReceivedMunicipality();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void showReceivedMunicipality() {

        // Receive the municipality name from Intent
        String receivedName = getIntent().getStringExtra(
                EXTRA_MUNICIPALITY
        );

        // Validate the received value
        TextProcessor processor = new TextProcessor();

        String validName =
                processor.processMunicipality(receivedName);

        // Handle missing or invalid data safely
        if (validName == null) {

            Toast.makeText(
                    this,
                    getString(R.string.error_missing_municipality),
                    Toast.LENGTH_SHORT
            ).show();

            finish();
            return;
        }

        // Find the municipality name TextView
        TextView municipalityNameTextView =
                findViewById(R.id.municipalityNameTextView);

        // Display the normalized name
        municipalityNameTextView.setText(validName);
    }

}