package com.walt.xu.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.walt.xu.myapplication.databinding.ActivityMainBinding;
import com.walt.xu.myapplication.entity.Person;
import com.walt.xu.myapplication.util.FileKit;
import com.walt.xu.myapplication.util.LocationUtils;
import com.walt.xu.myapplication.util.SharedPreferenceUtils;
import com.walt.xu.myapplication.util.Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    public static final String TYPE_SAVE = 1234 + "";
    public static final String TYPE_SAVE_2 = 1234 + "";
    private Button button;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ActivityMainBinding mBinding;
    public String zhangsan = "1223";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setContentView(R.layout.activity_main);
//跟布局里的gone的linelayout,一起使用
//        InitLeftFragment();
        SampleFragmentPagerAdapter sampleFragmentPagerAdapter = new SampleFragmentPagerAdapter(this, getSupportFragmentManager());
        mBinding.viewpagerTablayout.setAdapter(sampleFragmentPagerAdapter);
        mBinding.tablayout.setupWithViewPager(mBinding.viewpagerTablayout);
//        初始化变量
        initVarious();
    }

    private void InitLeftFragment() {
        button = (Button) this.findViewById(R.id.button);
        manager = this.getSupportFragmentManager();
        transaction = manager.beginTransaction();

        //创建fragment
        final LeftFragment leftFragment = new LeftFragment();
        transaction.add(R.id.left, leftFragment, "left");
        transaction.commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftFragment.getEditTextString(new LeftFragment.CallBack() {
                    @Override
                    public void getResult(String result) {
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void initVarious() {
        mBinding.toolBar.setTitle(R.string.bounce_dlv);
        mBinding.toolBar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        setSupportActionBar(mBinding.toolBar);
//        mBinding.toolBar.setNavigationIcon(R.mipmap.back_combined_shape);

        //设置是否有返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /**加载后默认选中某个 Tab
         * viewpager.setCurrentItem(1);z
         tablayout.getTabAt(1).select();
         */
        /**
         * 1、插入排序
         */
//        int array [] = {1,2,5,4,66,44};
//        for (int i = 0; i < array.length; i++) {
//            int tmp =array[i];
//            for (int j = i-1; j >=0 ; j--) {
//                if (array[j] > tmp) {
//                    array[j + 1] = array[j];
//                } else {
//                    break;
//                }
//                array[j] = tmp;
//            }
//
//        }
//        for (int k : array){
//            Log.i(TAG, "initVarious: "+k);
//        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "后退", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_secornd_fm:
                Intent intent = new Intent();
                intent.setClass(this, TwoActivity.class);
                startActivityForResult(intent, 2);
                break;
            case R.id.tv_save_file:
                ArrayList<String> strList = new ArrayList<>();
                strList.add(0, "lsy");
                FileKit.save(this, strList, TYPE_SAVE);
                SharedPreferenceUtils.setStringDataIntoSP("heartbeatlog", "lsykey", "lsyvalue");

                break;
            case R.id.tv_get_file:
//                ArrayList<String> str = (ArrayList<String>) FileKit.getObject(this, TYPE_SAVE);
//                String stringValueFromSP = SharedPreferenceUtils.getStringValueFromSP("heartbeatlog", "lsykey");
//                Log.i(TAG, "onClick: " + "-----" + str + "----------" + stringValueFromSP);

                // 开始定位
                LocationUtils mLocationUtils = LocationUtils.getInstance();
                mLocationUtils.setInstance(getApplicationContext());
                mLocationUtils.initLocation();
                String lat = SharedPreferenceUtils.getStringValueFromSP("Location", "latitude");

                Toast.makeText(this,"lat:"+lat,Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_first_fm:
                Intent intent1 = new Intent();
                intent1.setClass(this, ThreeActivity.class);
                Toast.makeText(getApplicationContext(),"跳到第三个activity",Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            Log.i(TAG, "onActivityResult: 3");
            mBinding.viewpagerTablayout.setCurrentItem(2);
            mBinding.tablayout.getTabAt(2).select();
        }
    }
}
