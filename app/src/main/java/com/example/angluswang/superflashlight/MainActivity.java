package com.example.angluswang.superflashlight;

import android.view.View;

public class MainActivity extends Bulb {

    // 右上角图标点击处理
    public void onclick_controller(View view) {
        hideAllUi();

        if (mCurrentType != uiType.UI_TYPE_UIMAIN) {
            uiMainLayout.setVisibility(View.VISIBLE);
            mCurrentType = uiType.UI_TYPE_UIMAIN;
            //停止警告灯
            mWarningLightFlicker = false;
            screenBrightness(mDefaultScreenBrightness / 255f); // 恢复屏幕亮度

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
                case UI_TYPE_MORSE:
                    uiMorse.setVisibility(View.VISIBLE);
                    mCurrentType = uiType.UI_TYPE_MORSE;
                    break;
                case UI_TYPE_BULB:
                    uiBulb.setVisibility(View.VISIBLE);
                    screenBrightness(1f);
                    mHideTextViewBulb.hide();
                    mCurrentType = uiType.UI_TYPE_BULB;

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

    }

    public void onClick_toPolice(View view) {

    }

    public void onClick_toSetting(View view) {

    }
}
