package com.example.angluswang.superflashlight;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

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
     * 从数据库表中查询桌面上是否已经有该快捷方式
     */
    private boolean shortcutInScreen() {

//        Cursor cursor = getContentResolver().query();
//        if (cursor.getCount() > 0) {
//            return true;
//        } else {
            return false;
//        }
    }

    /**
     * 添加快捷方式到桌面
     */
    public void onClick_AddShortcut(View view) {
        if (!shortcutInScreen()) {
            Intent installShortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            installShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "多功能手电筒");
            Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.app_icon);
            installShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

            Intent flashlightIntent = new Intent();
            flashlightIntent.setClassName("com.example.angluswang.superflashlight",
                    "com.example.angluswang.superflashlight.MainActivity");
            flashlightIntent.setAction(Intent.ACTION_MAIN);
            flashlightIntent.addCategory(Intent.CATEGORY_DEFAULT);

            installShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, flashlightIntent);
            sendBroadcast(installShortcut);
            Toast.makeText(this, "桌面快捷方式创建成功！",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "桌面已存在快捷方式，不用再添加了~",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 从桌面移除快捷方式
     */
    public void onClick_RemoveShortcut(View view) {
        if (shortcutInScreen()) {
            Intent removeShortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
            removeShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "多功能手电筒");

            Intent flashlightIntent = new Intent();
            flashlightIntent.setClassName("com.example.angluswang.superflashlight",
                    "com.example.angluswang.superflashlight.MainActivity");
            flashlightIntent.setAction(Intent.ACTION_MAIN);
            flashlightIntent.addCategory(Intent.CATEGORY_DEFAULT);

            removeShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, flashlightIntent);
            sendBroadcast(removeShortcut);
        } else {
            Toast.makeText(this, "没有快捷方式，无法删除！",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
