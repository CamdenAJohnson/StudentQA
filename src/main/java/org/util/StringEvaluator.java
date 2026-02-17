package org.util;

public class StringEvaluator {

    public static String evaluatePassowrd(String password) {
        if (password.isEmpty()) { return ""; }
        String errorMessage = "";
        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumericDigit = false;
        boolean foundSpecialChar = false;
        boolean foundLongEnough = false;
        boolean foundOtherChar = false;
        boolean processing = true;

        char currentChar = password.charAt(0);
        int currentCharIndex = 0;

        // WIP
        while (processing) {

            currentCharIndex++;
            if (currentCharIndex >= password.length()) {
                processing = false;
            } else {
                currentChar = password.charAt(currentCharIndex);
            }
        }

        if (!foundUpperCase) { errorMessage += "Upper case; "; }
        if (!foundLowerCase) { errorMessage += "Lower case; "; }
        if (!foundNumericDigit) { errorMessage += "Digit; "; }
        if (!foundSpecialChar) { errorMessage += "Special character; "; }
        if (!foundLongEnough) { errorMessage += "More than 5 characters; "; }
        if (!foundOtherChar) { errorMessage += "No non-standard characters; "; }
        if (!errorMessage.isEmpty()) { errorMessage = "You must have: " + errorMessage; }
        return errorMessage;
    }

    public static String evaluateUsername(String username) {
        if (username.isEmpty()) { return ""; }
        String errorMessage = "";
        boolean foundLongEnough = false;
        boolean foundSpecialChar = false;
        boolean foundOtherChar = false;
        boolean processing = true;

        char currentChar = username.charAt(0);
        int currentCharIndex = 0;

        // WIP
        while (processing) {

            currentCharIndex++;
            if (currentCharIndex >= username.length()) {
                processing = false;
            } else {
                currentChar = username.charAt(currentCharIndex);
            }
        }

        return errorMessage;
    }
}
