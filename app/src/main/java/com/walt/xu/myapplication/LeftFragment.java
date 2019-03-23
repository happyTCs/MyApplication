package com.walt.xu.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by walt on 2018/2/1.
 */

public class LeftFragment extends Fragment {

    private EditText editText;

    public LeftFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left, null);
        editText = (EditText) view.findViewById(R.id.editText1);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 接口回调
     */
    public void getEditTextString(CallBack callBack) {
        String msg = editText.getText().toString().trim();
        callBack.getResult(msg);
    }

    public interface CallBack{
         void getResult(String result);
    }

}
