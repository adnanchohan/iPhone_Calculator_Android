package com.app.calculator.View;

import android.util.Log;
import android.widget.TextView;

public class Utility {

    private static final String TAG = "Utility";

    public String  setCommasToDisplayView(String s) {
        try {
            String cleanString = s.replaceAll("[,]", "");

            // Split the string into integer and fractional parts
            String[] parts = cleanString.split("\\.");
            String integerPart = parts[0];
            String fractionalPart = parts.length > 1 ? parts[1] : "";

            // Format the integer part with commas if its length is greater than 3
            if (integerPart.length() > 3) {
                integerPart = addCommasToNumericString(integerPart);
            }

            // Reconstruct the formatted string
            String formattedString;
            if (fractionalPart.isEmpty() && s.contains(".")) {
                // Preserve the decimal point if it was present in the original string
                formattedString = integerPart + ".";
            } else {
                formattedString = fractionalPart.isEmpty() ? integerPart : integerPart + "." + fractionalPart;
            }

            Log.d(TAG, "Formatted String: " + formattedString);

            // Set the formatted text back to the TextView
            return formattedString;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to add commas to a numeric string.
     * @param numericString The input numeric string without commas.
     * @return The formatted string with commas.
     */
    private String addCommasToNumericString(String numericString) {
        StringBuilder stringBuilder = new StringBuilder(numericString);

        int length = numericString.length();
        int insertPosition = length - 3;

        while (insertPosition > 0) {
            stringBuilder.insert(insertPosition, ',');
            insertPosition -= 3;
        }

        return stringBuilder.toString();
    }

    public int getNumericLength(TextView textView) {
        // Get the text from the TextView
        String text = textView.getText().toString();

        // Remove all commas and periods
        String cleanedText = text.replaceAll("[,.]", "");

        // Calculate the length of the cleaned text

        return cleanedText.length();
    }
}
