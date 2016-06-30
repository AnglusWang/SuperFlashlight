package com.example.angluswang.superflashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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
    protected ImageView imgWarmingup;
    protected ImageView imgWarmingdown;
    protected EditText etMorseCode;
    protected ImageView mImageViewBulb;

    protected android.hardware.Camera mCamera;
    protected Camera.Parameters mParameters;

    protected FrameLayout uiFalshlight;
    protected LinearLayout uiMainLayout;
    protected LinearLayout uiWarnning;
    protected LinearLayout uiMorse;
    protected FrameLayout uiBulb;
    protected LinearLayout uiColorLight;
    protected LinearLayout uiPoliceLight;
    protected LinearLayout uiSetting;

    protected uiType mCurrentType = uiType.UI_TYPE_FLASH_LIGHT;
    protected uiType mLastType = uiType.UI_TYPE_FLASH_LIGHT;

    protected int mDefaultScreenBrightness;

    // 列举有哪些功能项
    protected enum uiType {
        UI_TYPE_UIMAIN,
        UI_TYPE_FLASH_LIGHT,
        UI_TYPE_WARNING_LIGHT,
        UI_TYPE_BULB,
        UI_TYPE_COLOR,
        UI_TYPE_MORSE,
        UI_TYPE_POLICE_LIGHT,
        UI_TYPE_SETTING
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mDefaultScreenBrightness = getScreenBrightness();
    }

    private void initView() {
        imgFlashController = (ImageView) findViewById(R.id.id_flashlight_controller);
        imgFlashlight = (ImageView) findViewById(R.id.id_flashlight);
        imgWarmingup = (ImageView) findViewById(R.id.img_warming_on);
        imgWarmingdown = (ImageView) findViewById(R.id.img_warming_off);
        etMorseCode = (EditText) findViewById(R.id.et_morse_code);
        mImageViewBulb = (ImageView) findViewById(R.id.imageview_bulb);

        uiFalshlight = (FrameLayout) findViewById(R.id.framelayout_flash_light);
        uiMainLayout = (LinearLayout) findViewById(R.id.linear_layout_main);
        uiWarnning = (LinearLayout) findViewById(R.id.linear_layout_warnning);
        uiMorse = (LinearLayout) findViewById(R.id.linear_layout_morse);
        uiBulb = (FrameLayout) findViewById(R.id.framelayout_bulb);

//        uiColorLight = (FrameLayout) findViewById(R.id.framelayout_color_light);
//        uiPoliceLight = (FrameLayout) findViewById(R.id.framelayout_police_light);
//        uiSetting = (FrameLayout) findViewById(R.id.framelayout_setting);
    }

    protected void hideAllUi() {
        uiFalshlight.setVisibility(View.GONE);
        uiMainLayout.setVisibility(View.GONE);
        uiWarnning.setVisibility(View.GONE);
        uiMorse.setVisibility(View.GONE);
        uiBulb.setVisibility(View.GONE);

//        uiColorLight.setVisibility(View.GONE);
//        uiPoliceLight.setVisibility(View.GONE);
//        uiSetting.setVisibility(View.GONE);
    }

    /**
     * 改变屏幕的亮度
     */
    public void screenBrightness(float value) {
        try {
            WindowManager.LayoutParams layout = getWindow().getAttributes();
            layout.screenBrightness = value;
            getWindow().setAttributes(layout);

        } catch (Exception e) {

        }
    }

    /**
     * 获得当前屏幕的亮度，获取的值为0~255的一个数值
     */
    public int getScreenBrightness() {
        int value = 0;
        try {
            value = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {

        }
        return value;
    }
}
