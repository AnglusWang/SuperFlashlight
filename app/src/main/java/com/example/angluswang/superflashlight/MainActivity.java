package com.example.angluswang.superflashlight;

import android.view.View;

public class MainActivity extends FlashActivity {

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

    public void onclick_toflashing(View view) {
        hideAllUi();
        uiFalshlight.setVisibility(View.VISIBLE);
        mCurrentType = uiType.UI_TYPE_FLASHLIGHT;
        mLastType = uiType.UI_TYPE_FLASHLIGHT;
    }
}
