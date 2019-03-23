package com.walt.xu.myapplication.callback;

import android.util.Log;

/**
 * Created by walt on 2018/3/21.
 */

public class Boss implements Jiekou {
    private static final String TAG = "Boss";
    
    @Override
    public void show() {
        Log.i(TAG, "show: 我知道了");
    }
}
