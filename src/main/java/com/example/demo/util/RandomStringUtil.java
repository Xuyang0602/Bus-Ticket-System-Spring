package com.example.demo.util;

public class RandomStringUtil {
    public static String getAlphaNumberString(int n, String inputStr) {
        // Chose a Character random from this String
        String inputStringUppercase = inputStr.trim().toUpperCase().replaceAll(" ", "").concat("123456789");

        // Create StringBuffer size of input string
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            // generate a random number between 0 to inputString variable length
            int index = (int) (inputStringUppercase.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(inputStringUppercase.charAt(index));
        }

        return sb.toString();
    }
}
