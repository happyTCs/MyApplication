package com.walt.xu.myapplication.application;

import android.app.Application;

import com.walt.xu.myapplication.andfix.AndFixPatchManager;

/**
 * Created by walt on 2019/2/21.
 */

public class ImoocApplicatin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化AndFix
        initAndFix();
    }

    private void initAndFix() {
        AndFixPatchManager.getInstance().initPatch(this);
    }
}
