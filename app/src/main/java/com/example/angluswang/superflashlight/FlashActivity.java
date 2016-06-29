package com.example.angluswang.superflashlight;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Jeson on 2016/5/30.
 */

public class FlashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFlashControllerSize();
    }

    /**
     * 检查设备是否支持闪光灯
     */
    public void check_flashlight(View v) {
        // 检测当前设备是否支持闪光灯
        if (!getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(FlashActivity.this, "没有闪光灯",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(FlashActivity.this, "可以使用闪光灯",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 根据屏幕来 动态 设置热区域的宽和高
     */
    private void setFlashControllerSize() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);  // 获得屏幕的像素

        ViewGroup.LayoutParams lp = imgFlashController.getLayoutParams();
        lp.width = point.x / 5;
        lp.height = point.y / 6;
        imgFlashController.setLayoutParams(lp);
    }
}
