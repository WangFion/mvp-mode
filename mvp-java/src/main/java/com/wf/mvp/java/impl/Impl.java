package com.wf.mvp.java.impl;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.View;

/**
 * MvpMode -> com.wf.mvp.java.imp -> Imp
 *
 * @Author: wf-pc
 * @Date: 2020-05-09 23:22
 * <p>
 * The top-level interface, used to limit the interfaces that the view and presenter must implement.
 */
public interface Impl {

    void onHandleMessage(Message msg);

    void runOnUiThread(Runnable runnable);

    Resources getResources();

    int getColor(int resId);

    String getString(int resId);

    String getString(int resId, Object... formatArgs);

    float getDimension(int resId);

    Drawable getDrawable(int resId);

    void showToast(String message);

    void showLongToast(String message);

    void showLoading();

    void showLoading(String message);

    void hideLoading();

    void toggleKeyboard();

    void showKeyboard(View view);

    void hideKeyboard();
}
