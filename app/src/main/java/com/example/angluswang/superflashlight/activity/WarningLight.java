package com.example.angluswang.superflashlight.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.angluswang.superflashlight.R;

/**
 * Created by Jeson on 2016/6/30.
 * 警告灯界面
 */

public class WarningLight extends FlashLight {
    protected boolean mWarningLightFlicker; //true:闪烁  false:不闪烁
    protected boolean mWarningLightState; //ture:on-off  false:off-on

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWarningLightFlicker = true;
    }

    class WarningLightThread extends Thread {
        @Override
        public void run() {
            mWarningLightFlicker = true;
            while (mWarningLightFlicker) {
                try {
                    Thread.sleep(mCurrentWarningLightInterval);
                    mWarningHandler.sendEmptyMessage(0);
                } catch (Exception e) {

                }
            }
        }
    }

    //因为在子线程中更新UI， 所以需要使用Handler处理消息
    private Handler mWarningHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mWarningLightState) {
                imgWarmingup.setImageResource(R.drawable.warning_light_on);
                imgWarmingdown.setImageResource(R.drawable.warning_light_off);
                mWarningLightState = false;
            } else {
                imgWarmingup.setImageResource(R.drawable.warning_light_off);
                imgWarmingdown.setImageResource(R.drawable.warning_light_on);
                mWarningLightState = true;
            }
        }
    };
}
