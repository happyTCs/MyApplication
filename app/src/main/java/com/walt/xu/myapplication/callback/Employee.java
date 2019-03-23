package com.walt.xu.myapplication.callback;

import android.util.Log;

/**
 * Created by walt on 2018/3/21.
 */

public class Employee {
    private static final String TAG = "Employee";
    Jiekou jiekou;

    public Employee zhece(Jiekou jiekou ,Employee e) {
        this.jiekou = jiekou;
        return e;
    }

    public void doSometing() {
        Log.i(TAG, "doSometing:拼命做事情 ");
        jiekou.show();
    }
}
