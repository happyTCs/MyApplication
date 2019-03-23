package com.walt.xu.myapplication.callback2;

/**
 * Created by walt on 2018/3/21.
 */

public class MyContent {
    private String a= "asdfhgfdgd";
    public void getString(CallBack callBack) {
        callBack.getLetter(a);
    }

    public interface CallBack {
        void getLetter(String str);
    }

}
