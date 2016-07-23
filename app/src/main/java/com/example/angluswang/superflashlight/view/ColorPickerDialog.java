package com.example.angluswang.superflashlight.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Jeson on 2016/7/1.
 * 颜色选择器对话框
 */

public class ColorPickerDialog extends Dialog {
    private final int COLOR_DIALOG_WIDTH = 300;     //对话框的宽度
    private final int COLOR_DIALOG_HEIGHT = 300;    //对话框的高度
    private final int CENTER_X = COLOR_DIALOG_WIDTH / 2;    //对话框中心圆的 x 坐标
    private final int CENTER_Y = COLOR_DIALOG_HEIGHT / 2;   //对话框中心圆的 y 坐标
    private final int CENTER_RADIUS = 32;   //中心圆半径

    public interface OnColorChangedListener {
        void colorChanged(int color);
    }

    private OnColorChangedListener mListener;
    private int mInitialColor;

    public ColorPickerDialog(Context context, OnColorChangedListener listener,
                             int initialColor) {
        super(context);
        mListener = listener;
        mInitialColor = initialColor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnColorChangedListener listener = new OnColorChangedListener() {
            @Override
            public void colorChanged(int color) {
                mListener.colorChanged(color);
                dismiss();  //关闭对话框
            }
        };

        setContentView(new ColorPickerView(getContext(), listener, mInitialColor));
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.BLACK);
        getWindow().setBackgroundDrawable(colorDrawable);

        getWindow().setAttributes(new WindowManager.LayoutParams(
                COLOR_DIALOG_WIDTH, COLOR_DIALOG_HEIGHT, 0, 0, 0
        ));

    }

    private class ColorPickerView extends View {

        private Paint mPaint, mCenterPaint;
        private final int[] mColors;
        private OnColorChangedListener mListener;

        // 是否触摸到中心了
        private boolean mTrackingCenter, mHightlightCenter;
        private static final float PI = 3.1415926f;

        public ColorPickerView(Context context, OnColorChangedListener listener,
                               int color) {
            super(context);
            mColors = new int[]{0xFFFF0000, 0xFFFF00FF, 0xFF0000FF,
                    0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00, 0xFFFF0000};
            Shader shader = new SweepGradient(0, 0, mColors, null);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setShader(shader);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(32);

            mCenterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mCenterPaint.setColor(color);
            mCenterPaint.setStrokeWidth(5);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //计算中心圆的半径
            float r = CENTER_X - mPaint.getStrokeWidth() * 0.5f - 20;
            //设置绘制中心
            canvas.translate(CENTER_X, CENTER_Y);

            canvas.drawCircle(0, 0, r, mPaint);
            canvas.drawCircle(0, 0, CENTER_RADIUS, mCenterPaint);

            if (mTrackingCenter) {
                int c = mCenterPaint.getColor();
                mCenterPaint.setStyle(Paint.Style.STROKE);
                if (mHightlightCenter) {
                    mCenterPaint.setAlpha(0xff);
                }else {
                    mCenterPaint.setAlpha(0x00);
                }

                canvas.drawCircle(0, 0, CENTER_RADIUS + mCenterPaint.getStrokeWidth(), mCenterPaint);
                mCenterPaint.setStyle(Paint.Style.FILL);
                mCenterPaint.setColor(c);
            }
        }

        /**
         * 求颜色平均值
         */
        private int average(int s, int d, float p) {
            return s + Math.round(p * (d - s));
        }

        private int interpColor(int colors[], float unit) {
            if (unit <= 0) {
                return colors[0];
            }
            if (unit >= 1) {
                return colors[colors.length - 1];
            }
            float p = unit * (colors.length - 1);
            int i = (int) p;
            p -= i;

            int c0 = colors[i];
            int c1 = colors[i + 1];
            int alpha = average(Color.alpha(c0), Color.alpha(c1), p);
            int red = average(Color.red(c0), Color.red(c1), p);
            int green = average(Color.green(c0), Color.green(c1), p);
            int blue = average(Color.blue(c0), Color.green(c1), p);
            return Color.argb(alpha, red, green, blue);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX() - CENTER_X;
            float y = event.getY() - CENTER_Y;

            boolean inCenter = Math.sqrt(x * x + y * y) <= CENTER_RADIUS;  //是否在内圆中
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTrackingCenter = inCenter;
                    if (inCenter) {
                        mHightlightCenter = true;
                        invalidate();
                        break;
                    }
                case MotionEvent.ACTION_MOVE:
                    // 弧度 反正切函数 -π ~ π
                    float angle = (float) Math.atan2(x, y);

                    // 把角度值映射到0 ~ 1 之间
                    float unit = angle / (2 * PI);
                    if (unit < 0) {
                        unit += 1;
                    }

                    mCenterPaint.setColor(interpColor(mColors, unit));
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    if (mTrackingCenter) {
                        if (inCenter) {
                            mListener.colorChanged(mCenterPaint.getColor());
                        }
                        mTrackingCenter = false;
                        invalidate();
                    }
                    break;
                default:
                    break;
            }

            return true;
        }
    }
}
