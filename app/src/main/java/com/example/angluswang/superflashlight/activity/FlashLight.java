package com.example.angluswang.superflashlight.activity;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Jeson on 2016/5/30.
 * 闪光灯界面
 */

public class FlashLight extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imgFlashlight.setTag(false); // 设置闪光灯 默认是关闭状态

        setFlashControllerSize();
    }

    /**
     * 检查设备是否支持闪光灯
     */
    public void check_flashlight(View v) {
        // 检测当前设备是否支持闪光灯
        if (!getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(FlashLight.this, "没有闪光灯",
                    Toast.LENGTH_SHORT).show();
            return;
        } else {
//            Toast.makeText(FlashLight.this, "可以使用闪光灯",
//                    Toast.LENGTH_SHORT).show();
        }

        // 打开、关闭闪光灯
        if ((Boolean) imgFlashlight.getTag() == false) {
            openFlash();
        } else {
            closeFlash();
        }
    }

    /**
     * 根据屏幕来 动态 设置热区域的宽和高
     */
    private void setFlashControllerSize() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);  // 获得屏幕的像素

        ViewGroup.LayoutParams lp = imgFlashController.getLayoutParams();
        lp.width = point.x / 3;
        lp.height = point.y * 3 / 4;
        imgFlashController.setLayoutParams(lp);
    }

    /**
     * 打开闪光灯
     */
    protected void openFlash() {
        TransitionDrawable transDraw = (TransitionDrawable) imgFlashlight.getDrawable();
        transDraw.startTransition(300);     // 播放图片到开启状态
        imgFlashlight.setTag(true);

        try {
            mCamera = android.hardware.Camera.open();

            int textureId = 0;
            mCamera.setPreviewTexture(new SurfaceTexture(textureId));
            mCamera.startPreview();

            mParameters = mCamera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

            mCamera.setParameters(mParameters);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭闪光灯
     */
    protected void closeFlash() {
        TransitionDrawable transDraw = (TransitionDrawable) imgFlashlight.getDrawable();
        if ((Boolean) imgFlashlight.getTag()) {
            transDraw.reverseTransition(300);   //回滚图片到关闭状态
            imgFlashlight.setTag(false);
            if (mCamera != null) {
                mParameters = mCamera.getParameters();
                mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);

                mCamera.setParameters(mParameters);

                //释放资源
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        //当前活动失去焦点时，自动关闭闪关灯
        closeFlash();
    }
}
