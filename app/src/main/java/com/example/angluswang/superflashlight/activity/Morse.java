package com.example.angluswang.superflashlight.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeson on 2016/6/30.
 * 发送摩尔斯电码
 */

public class Morse extends WarningLight {
    private final int DOT_TIME = 200;   //点停留的时间 单位：毫秒
    private final int LINE_TIME = DOT_TIME * 3; //线停留的时间
    private final int DOT_LINE_TIME = DOT_TIME; //点到线的时间间隔

    private final int CHAR_CHAR_TIME = DOT_TIME * 3;    //字符与字符之间的时间间隔
    private final int WORD_WORD_TIME = DOT_TIME * 7;    //单词与单词之间的时间间隔

    private String mMorseCode; //用于存储输入的摩尔斯电码

    /**
     * 要查询的电码表存入到程序中
     */
    private Map<Character, String> mMorseCodeMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMorseCodeMap.put('a', ".-");
        mMorseCodeMap.put('b', "-...");
        mMorseCodeMap.put('c', "-.-.");
        mMorseCodeMap.put('d', "-..");
        mMorseCodeMap.put('e', ".");
        mMorseCodeMap.put('f', "..-.");
        mMorseCodeMap.put('g', "--.");
        mMorseCodeMap.put('h', "....");
        mMorseCodeMap.put('i', "..");
        mMorseCodeMap.put('j', ".---");
        mMorseCodeMap.put('k', "-.-");
        mMorseCodeMap.put('l', ".-..");
        mMorseCodeMap.put('m', "--");
        mMorseCodeMap.put('n', "-.");
        mMorseCodeMap.put('o', "---");
        mMorseCodeMap.put('p', ".--.");
        mMorseCodeMap.put('q', "--.-");
        mMorseCodeMap.put('r', ".-.");
        mMorseCodeMap.put('s', "...");
        mMorseCodeMap.put('t', "-");
        mMorseCodeMap.put('u', "..-");
        mMorseCodeMap.put('v', "...-");
        mMorseCodeMap.put('w', ".--");
        mMorseCodeMap.put('x', "-..-");
        mMorseCodeMap.put('y', "-.--");
        mMorseCodeMap.put('z', "--..");

        mMorseCodeMap.put('0', "-----");
        mMorseCodeMap.put('1', ".----");
        mMorseCodeMap.put('2', "..---");
        mMorseCodeMap.put('3', "...--");
        mMorseCodeMap.put('4', "....-");
        mMorseCodeMap.put('5', ".....");
        mMorseCodeMap.put('6', "-....");
        mMorseCodeMap.put('7', "--...");
        mMorseCodeMap.put('8', "---..");
        mMorseCodeMap.put('9', "----.");
    }

    // 线程睡眠
    protected void sleepExt(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onClick_SendMorseCode(View v) {
        if (!getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(Morse.this, "没有闪光灯",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (vertifyMorseCode()) {
            sendSetence(mMorseCode);
        }
    }

    /**
     * 发送点
     */
    private void sendDot() {
        openFlash();
        sleepExt(DOT_TIME);
        closeFlash();
    }

    /**
     * 发送线
     */
    private void sendLine() {
        openFlash();
        sleepExt(LINE_TIME);
        closeFlash();
    }

    /**
     * 发送字符
     */
    private void sendChat(char c) {
        String morseCode = mMorseCodeMap.get(c);
        if (morseCode != null) {
            char lastChar = ' ';
            for (int i = 0, n = morseCode.length(); i < n; i++) {
                char dotLine = morseCode.charAt(i);
                if (dotLine == '.') {
                    sendDot();
                } else if (dotLine == '-') {
                    sendLine();
                }

                if (i > 0 && i < n - 1) {
                    if (lastChar == '.' && dotLine == '-') {
                        sleepExt(DOT_LINE_TIME);
                    }
                }
                lastChar = dotLine;
            }
        }
    }

    /**
     * 发送单词
     */
    private void sendWord(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sendChat(c);
            if (i < s.length() - 1) {
                sleepExt(CHAR_CHAR_TIME);
            }
        }
    }

    /**
     * 发送句子
     */
    private void sendSetence(String s) {
        String[] words = s.split(" +"); //正则表达式，表示至少一个空格，将字符串拆分成单词
        for (int i = 0, n = words.length; i < n; i++) {
            sendWord(words[i]);
            if (i < words.length - 1) {
                sleepExt(WORD_WORD_TIME);
            }
        }
        Toast.makeText(Morse.this, "摩尔斯电码发送完毕~",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * 检查输出的字符串是否符合发送摩尔斯电码的要求
     */
    private boolean vertifyMorseCode() {
        //获取文本框内容
        mMorseCode = etMorseCode.getText().toString().toLowerCase();

        //判断是否为空, 并提示用户重新输入
        if (TextUtils.isEmpty(mMorseCode)) {
            Toast.makeText(Morse.this, "你输入了无效的字符，请重新输入",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        //遍历每个字符，进行验证
        for (int i = 0, n = mMorseCode.length(); i < n; i++) {
            char c = mMorseCode.charAt(i);
            if (!(c >= 'a' && c <= 'z') && !(c >= '0' && c <= '9') && c != ' ') {
                Toast.makeText(this, "摩尔斯电码只能是字母、数字和空格！",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }

        return true;
    }
}
