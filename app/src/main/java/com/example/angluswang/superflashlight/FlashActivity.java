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

        imgFlashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 检测当前设备是否支持闪光灯
                if (!getPackageManager().hasSystemFeature(
                        PackageManager.FEATURE_CAMERA_FLASH)) {
                    Toast.makeText(FlashActivity.this, "没有闪光灯",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(FlashActivity.this, "可以使用闪光灯",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
