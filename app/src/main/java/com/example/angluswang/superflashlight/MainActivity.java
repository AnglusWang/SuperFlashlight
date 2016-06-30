package com.example.angluswang.superflashlight;

import android.view.View;

public class MainActivity extends FlashLight {

    // 右上角图标点击处理
    public void onclick_controller(View view) {
        hideAllUi();

        if (mCurrentType != uiType.UI_TYPE_UIMAIN) {
            uiMainLayout.setVisibility(View.VISIBLE);
            mCurrentType = uiType.UI_TYPE_UIMAIN;
        } else {
            switch (mLastType) {
                case UI_TYPE_FLASHLIGHT:
                    uiFalshlight.setVisibility(View.VISIBLE);
                    mCurrentType = uiType.UI_TYPE_FLASHLIGHT;
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
        mCurrentType = uiType.UI_TYPE_FLASHLIGHT;
        mLastType = uiType.UI_TYPE_FLASHLIGHT;
    }

    public void onClick_toMorse (View view) {

    }

    public void onClick_toBulb (View view) {

    }


    public void onClick_toColor (View view) {

    }

    public void onClick_toPolice (View view) {

    }

    public void onClick_toSetting (View view) {

    }
}
