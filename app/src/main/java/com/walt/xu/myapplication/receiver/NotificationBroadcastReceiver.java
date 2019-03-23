package com.walt.xu.myapplication.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by walt on 2018/3/9.
 */

public class NotificationBroadcastReceiver extends BroadcastReceiver {

    public static final String TYPE = "type"; //这个type是为了Notification更新信息的，这个不明白的朋友可以去搜搜，很多
        private static final String TAG = "NotificationBroadcastRe";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int type = intent.getIntExtra(TYPE, -1);

        if (type != -1) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(type);
        }

        if (action.equals("notification_clicked")) {
            //处理点击事件
            Log.i(TAG, "onReceive: "+"notification_clicked");
        }

        if (action.equals("notification_cancelled")) {
            //处理滑动清除和点击删除事件
            Log.i(TAG, "onReceive: "+"notification_cancelled");
        }
    }

}
