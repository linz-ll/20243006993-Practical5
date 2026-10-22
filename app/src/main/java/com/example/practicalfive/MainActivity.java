package com.example.practicalfive;

import android.os.Bundle;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupMunicipalityInput();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void setupMunicipalityInput() {

        EditText municipalityInput =
                findViewById(R.id.municipalityInput);

        TextProcessor processor = new TextProcessor();

        municipalityInput.setOnEditorActionListener(
                (textView, actionId, event) -> {

                    // Detect Go or physical Enter key
                    boolean isGo =
                            actionId == EditorInfo.IME_ACTION_GO;

                    boolean isEnter =
                            event != null
                                    && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                                    && event.getAction() == KeyEvent.ACTION_DOWN;

                    if (!isGo && !isEnter) {
                        return false;
                    }

                    // Read and validate user input
                    String userInput =
                            municipalityInput.getText().toString();

                    String validName =
                            processor.processMunicipality(userInput);

                    // Handle invalid input
                    if (validName == null) {

                        if (userInput.trim().isEmpty()) {
                            municipalityInput.setError(
                                    getString(R.string.error_empty_municipality)
                            );
                        } else {
                            municipalityInput.setError(
                                    getString(R.string.error_invalid_municipality)
                            );
                        }

                        municipalityInput.requestFocus();
                        return true;
                    }

                    // Create explicit intent
                    Intent intent = new Intent(
                            MainActivity.this,
                            Municipality.class
                    );

                    // Pass normalized municipality name
                    intent.putExtra(
                            Municipality.EXTRA_MUNICIPALITY,
                            validName
                    );

                    // Open second activity
                    startActivity(intent);

                    return true;
                }
        );
    }

}