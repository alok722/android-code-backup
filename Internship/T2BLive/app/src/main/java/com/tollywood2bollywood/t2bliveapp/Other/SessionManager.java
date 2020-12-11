package com.tollywood2bollywood.t2bliveapp.Other;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.tollywood2bollywood.t2bliveapp.MainActivity;


/**
 * Created by lucsonmacpc5 on 18/10/16.
 */
public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    private SharedPreferences pref;

    private SharedPreferences.Editor editor;
    private Context _context;
    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "T2Live";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_LANGUAGE = "Language";
    private static final String KEY_IS_COUNTRY = "Country";
    private static final String KEY_IS_CITY = "City";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
    }
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring LoginActivity Activity
        _context.startActivity(i);
    }
    public void delet(){
        editor.clear();
        editor.commit();
    }

    public void setTabName(String...strings){
        String str1 = strings[0];
        String str2 = strings[1];
        editor.putString(str1, str2);
        editor.commit();
    }

    public String getTabName(String str){
        return pref.getString(str, "");
    }






    public void setTab(String param){
        editor.putString("Tab", param);
        editor.commit();
    }

    public String getTab(){
        return pref.getString("Tab","");
    }
}