package com.walt.xu.myapplication.util.pop;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.walt.xu.myapplication.R;
import com.walt.xu.myapplication.databinding.PopupWindowGridBinding;
import com.walt.xu.myapplication.util.JsonUtils;

import java.util.List;

//import cn.chinapost.jdpt.pda.pcs.R;
//import cn.chinapost.jdpt.pda.pcs.databinding.PopupWindowGridBinding;
//import cn.chinapost.jdpt.pda.pcs.page.PageManager;
//import cn.chinapost.jdpt.pda.pcs.utils.JsonUtils;

public class SelectGridPopupWindow extends Activity {

    /**
     * Created by gxl on 2017/3/10.
     * 自定义底部弹出框界面
     * objList:
     * 角标0: Grid的列数
     * 角标1: 当前选中的select
     * 角标2：头部title
     * 角标3: adapter需要的List<String>
     */

    private PopupWindowGridBinding binding;
    private PopGridAdapter adapter;
    private int column, select;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         binding = DataBindingUtil.setContentView(this, R.layout.popup_window_grid);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initVariables();
    }

    private void initVariables() {
//        List<String> strList = JsonUtils.jsonArray2list(getIntent().getStringExtra(PageManager.JSON_BODY), String.class);
        String jumpString = getIntent().getStringExtra("jumpString");
        List<String> strList = JsonUtils.jsonArray2list(jumpString, String.class);
        if (strList != null) {
            column = Integer.parseInt(strList.get(0));
            select = Integer.parseInt(strList.get(1));
            binding.tvTitle.setText(strList.get(2));
            mList = JsonUtils.jsonArray2list(strList.get(3), String.class);
        } else {
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            finish();
        }

        initData();
        //添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity
//        binding.llPop.setOnClickListener(v -> {
//
//        });
    }

    private void initData() {
        binding.gvPop.setNumColumns(column);
        adapter = new PopGridAdapter(this, mList, select);
        binding.gvPop.setAdapter(adapter);

        binding.gvPop.setOnItemClickListener(
//                (parent, view, position, id) -> {

//        }
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    select = position;
                                    adapter.update(select);
                                    Intent intent = new Intent();
                                    intent.putExtra("select", select);
                                    setResult(100, intent);
                                    finish();
                    }
                }
        );

        binding.gvPop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select = i;
                adapter.update(select);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    //实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }
}