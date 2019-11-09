package com.jammi.akash.schoolbustracker.SupportFiles;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;


import com.jammi.akash.schoolbustracker.CustomClass.App;
import com.jammi.akash.schoolbustracker.R;

import java.io.File;


public class Constants {

    final public static int BUILD_TYPE_LIVE = 1;
    final public static int BUILD_TYPE_LIVE_DEMO = 2;
    final public static int BUILD_TYPE_STAGING = 3;
    final public static int BUILD_TYPE_LOCAL = 4;

    /**
     * By setting it to the particular build type the IP changes automatically and other configurations can be built on top of this environment constant
     * kindly replace the respective ips in line "26"
     */
    final public static int BUILD_TYPE = BUILD_TYPE_LOCAL;


//    final public static int color_selected = App.getAppContext().getResources().getColor(R.color.colorPrimaryDark);
//    final public static int color_deselected = App.getAppContext().getResources().getColor(R.color.bg_black);


    public static final String SERVER = ((BUILD_TYPE == BUILD_TYPE_LIVE) || (BUILD_TYPE == BUILD_TYPE_LIVE_DEMO)) ? "http://172.16.1.52:8000" : (BUILD_TYPE == BUILD_TYPE_STAGING) ? "staging_ip_here" : "local_ip_here";

//    public static final String APP_PACAGENAME = (BUILD_TYPE == BUILD_TYPE_LIVE) ? ValueUtils.APP_ID : "" + ValueUtils.APP_ID ;

    final public static String URL_LOGIN = SERVER + "/api/common/login/v2/";

    final public static String TBL_EXAMPLE = "example";

    public static final File app_storageDir = new File(getRootFolder(false));
    public static String CURRENT_ACTIVITY = ValueUtils.NOT_DEFINED;
    public static String FRAGMENT_NAME = "FRAGMENT_NAME";
    public static DbSQLiteHelper dbModel;
    public static Activity networkErrorAct = null;
    public static java.net.CookieManager msCookieManager = new java.net.CookieManager();
    public static String iscurrentChatViewId = "-1";
    public static Boolean ischatTabActive = false;

    public static Typeface font1_light, font1_regular, font1_bold;

    public static String password = null;

    public static String URL_REPORT_ERROR = Constants.SERVER + "/report_error_sample";


    public static String getRootFolder(Boolean isSlashRequired) {
        String path = Environment.getExternalStorageDirectory() + "/" + R.string.app_name;
        File folder = new File(path);
        if (!folder.exists()) folder.mkdir();
        if (isSlashRequired) return path + "/";
        else return path;
    }

    public static Typeface getFont1Bold(Context context) {
        if (font1_bold == null)
            font1_bold = Typeface.createFromAsset(context.getAssets(), "mavenpro_bold.ttf");
        return font1_bold;
    }


    public static Typeface getFont1Regular(Context context) {
        if (font1_regular == null)
            font1_regular = Typeface.createFromAsset(context.getAssets(), "mavenpro_regular.ttf");
        return font1_regular;
    }


    public static Typeface getFont1Light(Context context) {
        if (font1_light == null)
            font1_light = Typeface.createFromAsset(context.getAssets(), "mavenpro_medium.ttf");
        return font1_light;
    }



}
