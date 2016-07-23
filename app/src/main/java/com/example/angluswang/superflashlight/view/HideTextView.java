package com.example.angluswang.superflashlight.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Jeson on 2016/6/30.
 * 自定义隐藏文本控件
 */

public class HideTextView extends TextView {


    public HideTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                setVisibility(GONE);
            } else if (msg.what == 1) {
                setVisibility(VISIBLE);
            }
        }
    };

    class TextViewThread extends Thread {
        @Override
        public void run() {

            mHandler.sendEmptyMessage(1);
            try {
                sleep(3000);
                mHandler.sendEmptyMessage(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 启动线程
     */
    public void hide() {
        new TextViewThread().start();
    }
}
