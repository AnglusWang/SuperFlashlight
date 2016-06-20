package com.example.angluswang.superflashlight;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Jeson on 2016/5/30.
 *
 */

public class FlashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void openflashlight (View view) {
        if (!getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(this, "没有闪光灯",
                    Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "可以使用闪光灯", Toast.LENGTH_SHORT).show();
        }
    }
}
