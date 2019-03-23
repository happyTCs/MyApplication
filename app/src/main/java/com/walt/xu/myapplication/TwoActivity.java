package com.walt.xu.myapplication;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.NotificationCompat;

import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.widget.helper.ItemTouchUIUtil;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.walt.xu.myapplication.andfix.AndFixPatchManager;
import com.walt.xu.myapplication.callback.Boss;
import com.walt.xu.myapplication.callback.Employee;
import com.walt.xu.myapplication.callback.Jiekou;
import com.walt.xu.myapplication.callback2.MyContent;
import com.walt.xu.myapplication.databinding.ActivityTwoBinding;
import com.walt.xu.myapplication.receiver.NotificationBroadcastReceiver;
import com.walt.xu.myapplication.retrofit2.Constant;
import com.walt.xu.myapplication.retrofit2.RequestServices;
import com.walt.xu.myapplication.util.FileKit;
import com.walt.xu.myapplication.util.JsonUtils;
import com.walt.xu.myapplication.util.SharedPreferenceUtils;
import com.walt.xu.myapplication.util.Utils;
import com.walt.xu.myapplication.util.pop.SelectGridPopupWindow;

import org.xml.sax.helpers.LocatorImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.baidu.location.g.j.d;
import static com.baidu.location.g.j.v;

/**
 * Created by walt on 2018/2/5.
 */

public class TwoActivity extends Activity implements View.OnClickListener{
    private static final String TAG = "TwoActivity";
    private int count = 0;
    private ActivityTwoBinding mBinding;
    private NotificationManager notificationManager;
    public  int type = 1;
    private EditText editText;


    //界面初始化对象
//    private int select = AuthUtils.getFilePathCode();
    private int select = 0;
    //AndFix热修复
    private static final String FIEL_END =".apatch";
    private String mPatchDir;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_two);
       /* mBinding.tvTwoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(TwoActivity.this,MainActivity.class);
                Log.i(TAG, "onClick: 1");
                setResult(2);
                finish();

            }
        });*/
        initVariable();
        mPatchDir = getExternalCacheDir().getAbsolutePath() + "/apatch/";
        File file = new File(mPatchDir);
        if (file == null||file.exists()) {
            file.mkdir();
        }

    }

    private void initVariable() {
        mBinding.btnTest.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                List<String> strList1 = new ArrayList<>();
                strList1.add(String.valueOf(1));
                strList1.add(String.valueOf(select));
                strList1.add("选择环境");
                List<String> popList = new ArrayList<>();
                popList.clear();
//                if (AuthUtils.isTest()) {
                    popList.add("本地");
                    popList.add("开发");
                    popList.add("测试");
                    popList.add("集成");
                    popList.add("生产内网");
                    popList.add("生产外网");
                    popList.add("性能");
                    popList.add("培训");
//                } else {
//                    popList.add("生产内网");
//                    popList.add("生产外网");
//                }
                strList1.add(JsonUtils.Object2Json(popList));
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SelectGridPopupWindow.class);
                intent.putExtra("jumpString",JsonUtils.Object2Json(strList1));
                startActivityForResult(intent,10);
                return true;
            }
        });
    }

    private boolean reverse = false;
    public void rotate(View v) {
        float toDegree = reverse?-180f:180f;
        ObjectAnimator animator = ObjectAnimator
                .ofFloat(v,"rotation",0.0f,toDegree)
                .setDuration(400);
        animator.start();
        reverse =! reverse;
    }
    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_test:
//                onTest();
//                break;
//            case R.id.tv_two_back:
//                setResult(2);
//                finish();
//                break;
//            case R.id.btn_get_notification:
////                count++;
////                getNotification();
//                getAlertDialog();
//                break;
////            case R.id.fab_first:
////                float toDegree = reverse?-180f:180f;
////                ObjectAnimator animator = ObjectAnimator
////                        .ofFloat(v,"rotation",0.0f,toDegree)
////                        .setDuration(400);
////                animator.start();
////                reverse =! reverse;
////                break;
//           case  R.id.iv_jiekou:
////               Employee employee = new Employee();
////               employee.zhece(new Boss(),employee);
////               employee.doSometing();
//               MyContent myContent = new MyContent();
//                myContent.getString(new MyContent.CallBack() {
//                    @Override
//                    public void getLetter(String str) {
//                        Log.i(TAG, "getLetter: "+str);
//                    }
//                });
//               break;
//            case R.id.btn_coordinate:
////                Intent intent = new Intent(this, CoordinateActivity.class);
////                startActivity(intent);
////                2、实例化Retrofit
//                Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.URL_BASE).build();
////                3、通过Retrofit实例创建接口服务对象
//                RequestServices requestServices = retrofit.create(RequestServices.class);
////                4、接口服务对象调用接口中方法，获得Call对象
//                Call<ResponseBody> call = requestServices.getString();
////                5、Call对象执行请求（异步、同步请求）
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.isSuccess()) {
//                            try {
//
//                                String result = response.body().string();
//                                Log.i(TAG, "onResponse: "+result);
//                                //onResponse方法是运行在主线程也就是UI线程的，所以我们可以在这里
//                                //直接更新UI
//                                mBinding.tvTwoBack.setText(result);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.i(TAG, "onFailure: ");
//                    }
//                });
//                break;
//            case R.id.btn_dialog:
////                showAlertDialog();
//                generate();
//                break;
//        }
    }
    //生成.apatch
    private void generate() {
        Utils.printLog();
    }

    private void onTest() {


//        //        1、透明activity
//        Intent intent = new Intent();
//        intent.setClass(this,ThreeActivity.class);
//        startActivity(intent);
        // 2、window extend activity

        AndFixPatchManager.getInstance().addPatch(getPatchName());

    }

    private String getPatchName() {
        return mPatchDir.concat("imooc").concat(FIEL_END);
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TwoActivity.this);
        AlertDialog dialog = builder.create();
        View dialogView = View.inflate(this,R.layout.dialog_bounce_delivery,null);
        dialog.setView(dialogView);
        dialog.show();

    }

    private void getAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TwoActivity.this);
        View view = View.inflate(this, R.layout.dialog, null);
        builder.setTitle("输入订单号").setView(view);
        editText = (EditText) view.findViewById(R.id.etScanAddMail);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.i(TAG, "onClick: "+"点击");

            }
        }).show();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    private void getNotification() {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //发送通知
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tojson", "11");
        intent.putExtras(bundle);
         /* 第一个数是开始振动前的等待时间（震动关闭），
          第二个数是第一次开启振动的持续时间，
          第三个数是下一次关闭时间*/
        long[] vibrate = {0, 3000, 2000, 3000, 2000, 3000, 2000, 3000, 2000, 3000, 2000, 3000, 2000, 3000, 2000, 3000, 2000, 3000};
        PendingIntent p = PendingIntent.getActivity(this, count, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification builder = new Notification.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("【中国邮政】" )
                .setContentText("【中国邮政新一代】订单号：" )

                //.setContentInfo("补充内容")
                .setTicker("新消息")
                                .setAutoCancel(true)
                //.setDefaults(Notification.DEFAULT_VIBRATE) //默认震动
                //.setDefaults(Notification.DEFAULT_SOUND)//默认声音
                //自定义声音
                .setSound(Uri.parse("android.resource://cn.chinapost.jdpt.pda.pickup/" + R.raw.notification))
                //自定义震动
                .setVibrate(vibrate)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(p)
                //.setColor(Color.parseColor("#EAA935"))
                .setNumber(count)
                .build();
        //发送通知
        nm.notify(count, builder);
       /* Intent intentClick = new Intent(this, NotificationBroadcastReceiver.class);
        intentClick.setAction("notification_clicked");
        intentClick.putExtra(NotificationBroadcastReceiver.TYPE, type);
        PendingIntent pendingIntentClick = PendingIntent.getBroadcast(this, 0, intentClick, PendingIntent.FLAG_ONE_SHOT);

        Intent intentCancel = new Intent(this, NotificationBroadcastReceiver.class);
        intentCancel.setAction("notification_cancelled");
        intentCancel.putExtra(NotificationBroadcastReceiver.TYPE, type);
        PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(this, 0, intentCancel, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("中国邮政")
                .setContentText("新消息")
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntentClick)
                .setDeleteIntent(pendingIntentCancel);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(type *//* ID of notification *//*, notificationBuilder.build());  //这就是那个type，相同的update，不同add*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == 100) {
                    select = data.getIntExtra("select", select);
//                    if (AuthUtils.isTest()) {
//                        AuthUtils.saveFilePathCodeTest(select);
//                    } else {
//                        AuthUtils.saveFilePathCodeProduction(select);
//                    }
//                    AuthUtils.setFilePathCode(this);
//                    loginVM.showVName.set(showVersionName(AuthUtils.getFilePathCode()));
//                    loginVM.showVCode.set(BuildConfig.VERSION_NAME);
                    Toast.makeText(getApplicationContext(),select+"",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
