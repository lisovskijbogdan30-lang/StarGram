package org.telegram.messenger;

import android.content.Context;
import android.content.SharedPreferences;

public class StarGramSession {

    private static final String PREFS_NAME = "stargram_session";

    public static void save(String token) {
        SharedPreferences prefs = ApplicationLoader.applicationContext
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        prefs.edit()
                .putString("session_token", token)
                .apply();
    }

    public static String getToken() {
        SharedPreferences prefs = ApplicationLoader.applicationContext
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        return prefs.getString("session_token", null);
    }

    public static void clear() {
        SharedPreferences prefs = ApplicationLoader.applicationContext
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        prefs.edit()
                .clear()
                .apply();
    }

    public static boolean isLoggedIn() {
        return getToken() != null;
    }
}
