package com.example.angluswang.superflashlight.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Jeson on 2016/7/1.
 * 警灯
 */

public class PoliceLight extends ColorLight {

    protected boolean mPoliceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            int color = msg.what;
            uiPoliceLight.setBackgroundColor(color);
        }
    };

    class PoliceThread extends Thread {
        @Override
        public void run() {

            mPoliceState = true;
            while (mPoliceState) {
                mHandler.sendEmptyMessage(Color.BLUE);
                sleepExt(mCurrentPoliceLightInterval);
                mHandler.sendEmptyMessage(Color.BLACK);
                sleepExt(mCurrentPoliceLightInterval);
                mHandler.sendEmptyMessage(Color.RED);
                sleepExt(mCurrentPoliceLightInterval);
                mHandler.sendEmptyMessage(Color.BLACK);
                sleepExt(mCurrentPoliceLightInterval);
            }
        }
    }
}
