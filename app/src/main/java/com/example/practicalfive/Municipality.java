package com.example.practicalfive;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.ColorStateList;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

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

        // Receive municipality name from MainActivity
        String receivedName = getIntent().getStringExtra(
                EXTRA_MUNICIPALITY
        );

        // Validate and normalise the received name
        TextProcessor processor = new TextProcessor();

        String validName =
                processor.processMunicipality(receivedName);

        // Handle missing or invalid municipality
        if (validName == null) {

            Toast.makeText(
                    this,
                    getString(R.string.error_missing_municipality),
                    Toast.LENGTH_SHORT
            ).show();

            finish();
            return;
        }

        // Display the correctly capitalised municipality name
        TextView municipalityNameTextView =
                findViewById(R.id.municipalityNameTextView);

        municipalityNameTextView.setText(validName);

        // Update the image, information and theme colours
        configureMunicipalityDetails(validName);
    }


    private void configureMunicipalityDetails(String municipalityName) {

        // Step 1: Find UI components
        TextView nameTextView =
                findViewById(R.id.municipalityNameTextView);

        ImageView municipalityImageView =
                findViewById(R.id.municipalityImageView);

        TextView infoTextView =
                findViewById(R.id.municipalityInfoTextView);

        Button visitSiteButton =
                findViewById(R.id.visitSiteButton);


        // Step 2: Load municipality information array
        String[] municipalityHistories =
                getResources().getStringArray(
                        R.array.municipalities_array
                );


        // Step 3: Prepare resources to display
        int imageResource;
        int colorResource;
        int historyIndex;


        // Step 4: Select resources according to municipality
        switch (municipalityName) {

            case "Beijing":

                imageResource = R.drawable.beijing;
                colorResource = R.color.municipality_beijing;
                historyIndex = 0;
                break;

            case "Shanghai":

                imageResource = R.drawable.shanghai;
                colorResource = R.color.municipality_shanghai;
                historyIndex = 1;
                break;

            case "Chongqing":

                imageResource = R.drawable.chongqing;
                colorResource = R.color.municipality_chongqing;
                historyIndex = 2;
                break;

            case "Tianjin":

                imageResource = R.drawable.tianjin;
                colorResource = R.color.municipality_tianjin;
                historyIndex = 3;
                break;

            default:
                // Invalid names should already be rejected
                return;
        }


        // Step 5: Display the corresponding image
        municipalityImageView.setImageResource(imageResource);


        // Step 6: Display the corresponding history
        infoTextView.setText(municipalityHistories[historyIndex]);


        // Step 7: Retrieve the chosen theme colour
        int themeColor = ContextCompat.getColor(
                this,
                colorResource
        );


        // Step 8: Update title background
        nameTextView.setBackgroundColor(themeColor);


        // Step 9: Update information text colour
        infoTextView.setTextColor(themeColor);


        // Step 10: Update Visit site button background
        visitSiteButton.setBackgroundTintList(
                ColorStateList.valueOf(themeColor)
        );


        // Step 11: Ensure text remains readable
        int titleAndButtonTextColor;

        if (municipalityName.equals("Chongqing")) {

            titleAndButtonTextColor = ContextCompat.getColor(
                    this,
                    R.color.color_text_dark
            );

        } else {

            titleAndButtonTextColor = ContextCompat.getColor(
                    this,
                    R.color.color_text_light
            );
        }

        nameTextView.setTextColor(titleAndButtonTextColor);
        visitSiteButton.setTextColor(titleAndButtonTextColor);
    }



}