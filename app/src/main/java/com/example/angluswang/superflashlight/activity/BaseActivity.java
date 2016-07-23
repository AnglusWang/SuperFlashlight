package com.example.angluswang.superflashlight.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.angluswang.superflashlight.view.HideTextView;
import com.example.angluswang.superflashlight.R;

/**
 * Created by Jeson on 2016/5/30.
 * 基类
 */

public class BaseActivity extends Activity {

    // 列举有哪些功能项
    protected enum uiType {
        UI_TYPE_UIMAIN, UI_TYPE_FLASH_LIGHT, UI_TYPE_WARNING_LIGHT, UI_TYPE_BULB,
        UI_TYPE_COLOR, UI_TYPE_MORSE, UI_TYPE_POLICE_LIGHT, UI_TYPE_SETTING
    }

    protected ImageView imgFlashlight;
    protected ImageView imgFlashController;
    protected ImageView imgWarmingup;
    protected ImageView imgWarmingdown;
    protected EditText etMorseCode;
    protected ImageView imgViewBulb;
    protected HideTextView mHideTextViewBulb;
    protected HideTextView mHideTextViewColor;
    protected HideTextView mHideTextViewPoliceLight;

    protected SeekBar skWarningLight;
    protected SeekBar skPoliceLight;
    protected Button btnAddShortcut;
    protected Button btnRemoveShortcut;

    protected android.hardware.Camera mCamera;
    protected Camera.Parameters mParameters;

    protected FrameLayout uiFalshlight;
    protected LinearLayout uiMainLayout;
    protected LinearLayout uiWarnning;
    protected LinearLayout uiMorse;
    protected FrameLayout uiBulb;
    protected FrameLayout uiColorLight;
    protected FrameLayout uiPoliceLight;
    protected LinearLayout uiSetting;

    protected uiType mCurrentType = uiType.UI_TYPE_FLASH_LIGHT;
    protected uiType mLastType = uiType.UI_TYPE_FLASH_LIGHT;

    protected int mDefaultScreenBrightness;

    protected int mFinishCount = 0; // 实现按返回键计数

    protected int mCurrentWarningLightInterval = 500;   //警告灯的闪烁间隔
    protected int mCurrentPoliceLightInterval = 500;    //警灯的闪烁间隔

    protected SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mDefaultScreenBrightness = getScreenBrightness();

        skWarningLight.setProgress(mCurrentWarningLightInterval - 50);
        skPoliceLight.setProgress(mCurrentPoliceLightInterval - 100);

        mSharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        mCurrentWarningLightInterval = mSharedPreferences.getInt("warning_light_interval", 200);
        mCurrentPoliceLightInterval = mSharedPreferences.getInt("police_light_interval", 100);
    }

    private void initView() {
        imgFlashController = (ImageView) findViewById(R.id.id_flashlight_controller);
        imgFlashlight = (ImageView) findViewById(R.id.id_flashlight);
        imgWarmingup = (ImageView) findViewById(R.id.img_warming_on);
        imgWarmingdown = (ImageView) findViewById(R.id.img_warming_off);
        etMorseCode = (EditText) findViewById(R.id.et_morse_code);
        imgViewBulb = (ImageView) findViewById(R.id.iv_bulb);
        mHideTextViewBulb = (HideTextView) findViewById(R.id.tv_hide_bulb);
        mHideTextViewColor = (HideTextView) findViewById(R.id.tv_hide_color);
        mHideTextViewPoliceLight = (HideTextView) findViewById(R.id.tv_hide_police_light);

        // 设置界面的控件初始化
        skWarningLight = (SeekBar) findViewById(R.id.seekbar_warning_light);
        skPoliceLight = (SeekBar) findViewById(R.id.seekbar_police_light);
        btnAddShortcut = (Button) findViewById(R.id.button_add_shortcut);
        btnRemoveShortcut = (Button) findViewById(R.id.button_remove_shortcut);

        uiFalshlight = (FrameLayout) findViewById(R.id.framelayout_flash_light);
        uiMainLayout = (LinearLayout) findViewById(R.id.linear_layout_main);
        uiWarnning = (LinearLayout) findViewById(R.id.linear_layout_warnning);
        uiMorse = (LinearLayout) findViewById(R.id.linear_layout_morse);
        uiBulb = (FrameLayout) findViewById(R.id.framelayout_bulb);
        uiColorLight = (FrameLayout) findViewById(R.id.framelayout_color_light);
        uiPoliceLight = (FrameLayout) findViewById(R.id.framelayout_police_light);
        uiSetting = (LinearLayout) findViewById(R.id.linear_layout_setting);

    }

    protected void hideAllUi() {
        uiFalshlight.setVisibility(View.GONE);
        uiMainLayout.setVisibility(View.GONE);
        uiWarnning.setVisibility(View.GONE);
        uiMorse.setVisibility(View.GONE);
        uiBulb.setVisibility(View.GONE);
        uiColorLight.setVisibility(View.GONE);
        uiPoliceLight.setVisibility(View.GONE);
        uiSetting.setVisibility(View.GONE);
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mFinishCount = 0;
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 重写该方法，实现两次按返回键退出功能
     */
    @Override
    public void finish() {
        mFinishCount++;
        if (mFinishCount == 1) {
            Toast.makeText(this, "再按一次退出!",
                    Toast.LENGTH_SHORT).show();
        } else if (mFinishCount == 2) {
            super.finish();
        }
    }
}
