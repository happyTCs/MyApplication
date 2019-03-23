package com.walt.xu.myapplication.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import com.walt.xu.myapplication.andfix.AndFixPatchManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mlp5474 on 2016/8/27.
 */
public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    private static BaseApplication baseApplication;
    private static Context mContext;
    //用于存放我们所有activity的数组
    public static List<Activity> activities;
    //向集合中添加一个activity
    public static void addActivity(Activity activity) {
        if (activities == null) {
            //如果集合为空  则初始化
            activities = new ArrayList<>();
        }
        //将activity加入到集合中
        activities.add(activity);
    }

    //结束掉所有的Activity
    public static void finishAll() {
        //循环集合,  将所有的activity finish
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void removeActivity(Activity activity) {
        //移除已经销毁的Activity
        activities.remove(activity);

    }
    public BaseApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        // 开始定位
        LocationUtils mLocationUtils = LocationUtils.getInstance();
        mLocationUtils.setInstance(mContext);
        mLocationUtils.initLocation();
        initAndFix();
    }
    private void initAndFix() {
        AndFixPatchManager.getInstance().initPatch(this);
    }
    public static BaseApplication getInstance() {
        if (null == baseApplication) {
            baseApplication = new BaseApplication();
        }
        return baseApplication;
    }
    public static Context getContext() {
        return mContext;
    }

}
