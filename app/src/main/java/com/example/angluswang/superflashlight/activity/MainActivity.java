package com.example.angluswang.superflashlight.activity;

import android.graphics.Color;
import android.view.View;

import com.example.angluswang.superflashlight.activity.Settings;

public class MainActivity extends Settings {

    // 右上角图标点击处理
    public void onclick_controller(View view) {
        hideAllUi();

        if (mCurrentType != uiType.UI_TYPE_UIMAIN) {
            uiMainLayout.setVisibility(View.VISIBLE);
            mCurrentType = uiType.UI_TYPE_UIMAIN;
            //停止警告灯
            mWarningLightFlicker = false;
            screenBrightness(mDefaultScreenBrightness / 255f); // 恢复屏幕亮度

            if (mBulbCrossFadeFlag) {
                mDrawable.reverseTransition(0);
            }
            mBulbCrossFadeFlag = false;

            mPoliceState = false;

            //保存数据
            mSharedPreferences.edit().putInt("warning_light_interval", mCurrentWarningLightInterval)
                    .putInt("police_light_interval", mCurrentPoliceLightInterval).commit();

        } else {
            switch (mLastType) {
                case UI_TYPE_FLASH_LIGHT:
                    uiFalshlight.setVisibility(View.VISIBLE);
                    mCurrentType = uiType.UI_TYPE_FLASH_LIGHT;
                    break;

                case UI_TYPE_WARNING_LIGHT:
                    screenBrightness(1f);
                    uiWarnning.setVisibility(View.VISIBLE);
                    mCurrentType = uiType.UI_TYPE_WARNING_LIGHT;
                    new WarningLightThread().start();
                    break;

                case UI_TYPE_MORSE:
                    uiMorse.setVisibility(View.VISIBLE);
                    mCurrentType = uiType.UI_TYPE_MORSE;
                    break;

                case UI_TYPE_BULB:
                    uiBulb.setVisibility(View.VISIBLE);
                    screenBrightness(1f);
                    mCurrentType = uiType.UI_TYPE_BULB;
                    break;

                case UI_TYPE_COLOR:
                    uiColorLight.setVisibility(View.VISIBLE);
                    screenBrightness(1f);
                    mCurrentType = uiType.UI_TYPE_COLOR;
                    break;

                case UI_TYPE_POLICE_LIGHT:
                    uiPoliceLight.setVisibility(View.VISIBLE);
                    screenBrightness(1f);
                    mCurrentType = uiType.UI_TYPE_POLICE_LIGHT;
                    new PoliceThread().start();
                    break;
                case UI_TYPE_SETTING:
                    uiSetting.setVisibility(View.VISIBLE);
                    mCurrentType = uiType.UI_TYPE_SETTING;
                    break;
                default:
                    break;
            }
        }
    }

    // 闪光灯图标点击处理
    public void onClick_toFlashLight(View view) {
        hideAllUi();
        uiFalshlight.setVisibility(View.VISIBLE);
        mCurrentType = uiType.UI_TYPE_FLASH_LIGHT;
        mLastType = uiType.UI_TYPE_FLASH_LIGHT;
    }

    public void onClick_toWarnnig(View view) {
        hideAllUi();
        uiWarnning.setVisibility(View.VISIBLE);
        mCurrentType = uiType.UI_TYPE_WARNING_LIGHT;
        mLastType = mCurrentType;

        screenBrightness(1f);
        new WarningLightThread().start();
    }

    public void onClick_toMorse(View view) {
        hideAllUi();
        uiMorse.setVisibility(View.VISIBLE);
        mCurrentType = uiType.UI_TYPE_MORSE;
        mLastType = mCurrentType;
    }

    public void onClick_toBulb(View view) {
        hideAllUi();
        uiBulb.setVisibility(View.VISIBLE);
        screenBrightness(1f);
        mHideTextViewBulb.hide();
        mCurrentType = uiType.UI_TYPE_BULB;
        mLastType = mCurrentType;
    }


    public void onClick_toColor(View view) {
        hideAllUi();
        uiColorLight.setVisibility(View.VISIBLE);
        screenBrightness(1f);
        mHideTextViewColor.setTextColor(Color.rgb(
                255 - Color.red(mCurrentColorlight),
                255 - Color.green(mCurrentColorlight),
                255 - Color.blue(mCurrentColorlight)));
        mHideTextViewColor.hide();
        mCurrentType = uiType.UI_TYPE_COLOR;
        mLastType = mCurrentType;
    }

    public void onClick_toPolice(View view) {
        hideAllUi();
        uiPoliceLight.setVisibility(View.VISIBLE);
        screenBrightness(1f);
        mCurrentType = uiType.UI_TYPE_POLICE_LIGHT;
        mLastType = mCurrentType;

        mHideTextViewPoliceLight.hide();
        new PoliceThread().start();
    }

    public void onClick_toSetting(View view) {
        hideAllUi();
        uiSetting.setVisibility(View.VISIBLE);
        mCurrentType = uiType.UI_TYPE_SETTING;
        mLastType = mCurrentType;
    }
}
