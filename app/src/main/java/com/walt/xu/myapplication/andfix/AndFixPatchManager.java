package com.walt.xu.myapplication.andfix;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;
import com.walt.xu.myapplication.util.Utils;

import java.io.IOException;

/**
 * Created by walt on 2019/2/21.
 * @function 管理
 */

public class AndFixPatchManager {
    private static AndFixPatchManager mInstance = null;
    private static PatchManager mPatchManager;

    public static AndFixPatchManager getInstance() {
        if (mInstance == null) {
            synchronized (AndFixPatchManager.class) {
                if (mInstance == null) {
                    mInstance = new AndFixPatchManager();
                }
            }
        }
        return mInstance;
    }

    //初始化AndFix方法
    public void initPatch(Context context) {
         mPatchManager = new PatchManager(context);
        mPatchManager.init(Utils.getVersionName(context));
        mPatchManager.loadPatch();
    }
    //加载我们的Patch文件
    public void addPatch(String path) {
        try {
            if (mPatchManager != null) {
                mPatchManager.addPatch(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
