package com.example.angluswang.superflashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Jeson on 2016/7/1.
 * 彩色灯
 */

public class ColorLight extends Bulb {

    protected int mCurrentColorlight = Color.RED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onClick_ShowColorPicker(View view) {

    }
}
