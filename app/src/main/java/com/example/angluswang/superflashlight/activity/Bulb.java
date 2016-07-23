package com.example.angluswang.superflashlight.activity;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Jeson on 2016/6/30.
 * 发光的电灯泡
 */

public class Bulb extends Morse {
    protected boolean mBulbCrossFadeFlag;
    protected TransitionDrawable mDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawable = (TransitionDrawable) imgViewBulb.getDrawable();
    }

    public void onClick_BulbCrossFade(View view) {

        if (!mBulbCrossFadeFlag) {
            mDrawable.startTransition(500);
            mBulbCrossFadeFlag = true;
            screenBrightness(1f);
        } else {
            mDrawable.reverseTransition(500);
            mBulbCrossFadeFlag = false;
            screenBrightness(0f);

        }
    }
}
