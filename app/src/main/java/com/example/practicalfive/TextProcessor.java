package com.example.practicalfive;


public class TextProcessor {

    public String processMunicipality(String municipality) {

        // Step 1: Check for null input
        if (municipality == null) {
            return null;
        }

        // Step 2: Remove leading and trailing spaces
        String cleanedInput = municipality.trim();

        // Step 3: Check for empty input
        if (cleanedInput.isEmpty()) {
            return null;
        }

        // Step 4: Validate and normalise municipality names
        if (cleanedInput.equalsIgnoreCase("Beijing")) {
            return "Beijing";

        } else if (cleanedInput.equalsIgnoreCase("Shanghai")) {
            return "Shanghai";

        } else if (cleanedInput.equalsIgnoreCase("Chongqing")) {
            return "Chongqing";

        } else if (cleanedInput.equalsIgnoreCase("Tianjin")) {
            return "Tianjin";

        }

        // Step 5: Reject unsupported municipalities
        return null;
    }
}
