package com.walt.xu.myapplication.util;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.walt.xu.myapplication.R;

import org.xml.sax.helpers.LocatorImpl;


/**
 * Created by walt on 2018/2/7.
 */

public class PostEditText extends AppCompatEditText {
   private Drawable imgInable;
    private Context mContext;

    public PostEditText(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public PostEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public PostEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
         imgInable = ContextCompat.getDrawable(mContext, R.drawable.clear);
         addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {

             }

             @Override
             public void afterTextChanged(Editable s) {
                 setDrawable();
             }
         });
         setDrawable();
    }
//设置删除图片
    private void setDrawable() {
        if (length()<1)
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        else
            setCompoundDrawablesWithIntrinsicBounds(null,null,imgInable,null);
    }

//处理删除事件

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgInable!=null&&event.getAction() == MotionEvent.ACTION_UP) {
            int eventX  = (int) event.getRawX();
            int eventY  = (int) event.getRawY();
            Log.d("TAG0", "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            Log.d("TAG1", "rect.left = " + rect.left + "; rect.right = " + rect.right);
            rect.left= rect.right-200;
            Log.d("TAG2", "rect.left = " + rect.left + "; rect.right = " + rect.right);

            if (rect.contains(eventX, eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}
