package com.example.angluswang.superflashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Jeson on 2016/7/1.
 * 彩色灯
 */

public class ColorLight extends Bulb
        implements ColorPickerDialog.OnColorChangedListener {

    protected int mCurrentColorlight = Color.RED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void colorChanged(int color) {
        mCurrentColorlight = color;
        uiColorLight.setBackgroundColor(color);
    }

    public void onClick_ShowColorPicker(View view) {
        Toast.makeText(ColorLight.this, "屏幕被点击~",
                Toast.LENGTH_SHORT).show();
        new ColorPickerDialog(ColorLight.this, ColorLight.this, Color.RED).show();
    }
}
