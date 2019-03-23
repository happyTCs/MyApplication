package com.walt.xu.myapplication.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by walt on 2019/2/21.
 */

public class Utils {

    public static String getVersionName(Context context) {
        String versionName ="1.0.0";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(),0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void printLog() {
        String error = null;
        Log.e("renzhiqiang",error);
    }
}
