package com.example.angluswang.superflashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Jeson on 2016/5/30.
 * 基类
 */

public class BaseActivity extends Activity {
    protected ImageView imgFlashlight;
    protected ImageView imgFlashController;

    protected android.hardware.Camera mCamera;
    protected Camera.Parameters mParameters;

    protected FrameLayout uiFalshlight;
    protected LinearLayout uiMainLayout;


    protected FrameLayout uiBulb;
    protected FrameLayout uiColorLight;
    protected FrameLayout uiMorse;
    protected FrameLayout uiPoliceLight;
    protected FrameLayout uiSetting;
    protected FrameLayout uiWarnning;

    protected uiType mCurrentType = uiType.UI_TYPE_FLASHLIGHT;
    protected uiType mLastType = uiType.UI_TYPE_FLASHLIGHT;


    // 列举有哪些功能项
    protected enum uiType {
        UI_TYPE_UIMAIN,
        UI_TYPE_FLASHLIGHT,
        UI_TYPE_WARNING_LIGHT,
        UI_TYPE_BULB,
        UI_TYPE_COLOR,
        UI_TYPE_POLICE,
        UI_TYPE_POLICE_LIGHT,
        UI_TYPE_SETTING
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        imgFlashController = (ImageView) findViewById(R.id.id_flashlight_controller);
        imgFlashlight = (ImageView) findViewById(R.id.id_flashlight);

        uiFalshlight = (FrameLayout) findViewById(R.id.framelayout_flash_light);
        uiMainLayout = (LinearLayout) findViewById(R.id.framelayout_main);

        uiBulb = (FrameLayout) findViewById(R.id.framelayout_bulb);
        uiColorLight = (FrameLayout) findViewById(R.id.framelayout_color_light);
        uiMorse = (FrameLayout) findViewById(R.id.framelayout_morse);
        uiPoliceLight = (FrameLayout) findViewById(R.id.framelayout_police_light);
        uiSetting = (FrameLayout) findViewById(R.id.framelayout_setting);
        uiWarnning = (FrameLayout) findViewById(R.id.framelayout_warnning);
    }

    protected void hideAllUi() {
        uiFalshlight.setVisibility(View.GONE);
        uiMainLayout.setVisibility(View.GONE);

//        uiBulb.setVisibility(View.GONE);
//        uiColorLight.setVisibility(View.GONE);
//        uiMorse.setVisibility(View.GONE);
//        uiPoliceLight.setVisibility(View.GONE);
//        uiSetting.setVisibility(View.GONE);
//        uiWarnning.setVisibility(View.GONE);
    }
}
