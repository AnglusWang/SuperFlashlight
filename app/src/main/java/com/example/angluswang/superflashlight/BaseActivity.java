package com.example.angluswang.superflashlight;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Jeson on 2016/5/30.
 * 基类
 */

public class BaseActivity extends Activity {
    protected ImageView imgFlashlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        imgFlashlight = (ImageView) findViewById(R.id.img_flashlight);
    }
}
