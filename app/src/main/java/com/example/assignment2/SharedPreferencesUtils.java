package com.example.assignment2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class SharedPreferencesUtils {

    private static final String PREFS_NAME = "MyPrefs";

    // ... Other methods for saving data

    public static void saveStringArrayList(Context context, String key, ArrayList<String> arrayList) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Convert the ArrayList to a string
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : arrayList) {
            stringBuilder.append(item).append(",");
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1); // Remove the last comma
        }

        editor.putString(key, stringBuilder.toString());
        editor.apply();
    }

    public static ArrayList<String> loadStringArrayList(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Retrieve the serialized string
        String serializedString = prefs.getString(key, "");

        // Convert the serialized string back to an ArrayList
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(serializedString.split(",")));

        // Remove any empty or null values
        arrayList.removeAll(Arrays.asList("", null));

        return arrayList;
    }
}
