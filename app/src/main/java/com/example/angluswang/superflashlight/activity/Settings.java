package com.example.angluswang.superflashlight.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.angluswang.superflashlight.R;
import com.example.angluswang.superflashlight.utils.ShortCutUtil;

/**
 * Created by Jeson on 2016/7/1.
 * 设置，用于设置警示灯和警告灯的闪烁频率等
 */

public class Settings extends PoliceLight
        implements SeekBar.OnSeekBarChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skWarningLight.setOnSeekBarChangeListener(this);
        skPoliceLight.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekbar_warning_light:
                mCurrentWarningLightInterval = progress + 100;

                break;
            case R.id.seekbar_police_light:
                mCurrentPoliceLightInterval = progress + 50;

                break;
            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 添加快捷方式到桌面
     */
    public void onClick_AddShortcut(View view) {
        if (ShortCutUtil.hasShortcut(this)) {
            Toast.makeText(this, "桌面已存在快捷方式，不用再添加了~",
                    Toast.LENGTH_SHORT).show();
        } else {
            ShortCutUtil.addShortcut(this, R.drawable.app_icon);
            Toast.makeText(this, "桌面快捷方式创建成功！",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 从桌面移除快捷方式
     */
    public void onClick_RemoveShortcut(View view) {
        if (ShortCutUtil.hasShortcut(this)) {
            ShortCutUtil.delShortcut(this);
        } else {
            Toast.makeText(this, "没有快捷方式，无法删除！",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
