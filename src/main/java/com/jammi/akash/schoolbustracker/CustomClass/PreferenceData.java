package com.jammi.akash.schoolbustracker.CustomClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceData {
    static final String PREF_LOGGEDIN_USER_EMAIL = "logged_in_email";
    static final String PREF_USER_LOGGEDIN_STATUS = "logged_in_status";

    public static SharedPreferences getSharedPreferences(Context ctx)
    {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setLoggedInUserEmail(Context ctx, String email)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_LOGGEDIN_USER_EMAIL, email);
        editor.commit();
    }

    public static String getLoggedInEmailUser(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_LOGGEDIN_USER_EMAIL, "");
    }

    public static void setUserLoggedInStatus(Context ctx, boolean status)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_USER_LOGGEDIN_STATUS, status);
        editor.commit();
    }

    public static void setlatandLog(Context ctx, Double lat,Double log)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("lat", ""+lat);
        editor.putString("log", ""+log);
        editor.commit();
    }

    public static void setdriverlatandLog(Context ctx, Double lat,Double log)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("driver_lat", ""+lat);
        editor.putString("driver_log", ""+log);
        editor.commit();
    }
    public static String getDriverLat(Context ctx)
    {
        return getSharedPreferences(ctx).getString("driver_lat", "");
    }
    public static String getDriverLog(Context ctx)
    {
        return getSharedPreferences(ctx).getString("driver_log", "");
    }







    public static String getLat(Context ctx)
    {
        return getSharedPreferences(ctx).getString("lat", "");
    }
    public static String getLog(Context ctx)
    {
        return getSharedPreferences(ctx).getString("log", "");
    }




    public static boolean getUserLoggedInStatus(Context ctx)
    {
        return getSharedPreferences(ctx).getBoolean(PREF_USER_LOGGEDIN_STATUS, false);
    }

    public static void clearLoggedInEmailAddress(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(PREF_LOGGEDIN_USER_EMAIL);
        editor.remove(PREF_USER_LOGGEDIN_STATUS);
        editor.commit();
    }
}
