package com.wf.mvp.java.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.wf.mvp.java.customize.UiHandler;
import com.wf.mvp.java.impl.Impl;

/**
 * MvpMode -> com.wf.mvp.java.view -> NActivity
 *
 * @Author: wf-pc
 * @Date: 2020-05-27 18:08
 * <p>
 * Mvp's v-layer basic no presenter Activity, used to implement common functions.
 */
public abstract class NActivity extends Activity implements Impl {

    /**
     * The instance object of UI thread handler.
     */
    protected UiHandler mUiHandler;

    private Toast mToast;
    private ProgressDialog mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        beforeCreate();
        super.onCreate(savedInstanceState);
        mUiHandler = new UiHandler();
        mUiHandler.attachRefs(this);
        setContentView(bindLayoutId());
        initView(getIntent());
        initListener();
        initData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUiHandler != null) {
            mUiHandler.detachRefs();
            mUiHandler = null;
        }
        release();
    }


    /**
     * Used to do something before Create.
     */
    protected void beforeCreate() {
    }

    /**
     * Used to bind layout files.
     */
    protected abstract int bindLayoutId();

    /**
     * Used to initialize view.
     */
    protected abstract void initView(Intent intent);

    /**
     * Used to initialize listener.
     */
    protected abstract void initListener();

    /**
     * Used to initialize data.
     */
    protected abstract void initData();

    /**
     * Used to release resources.
     */
    protected abstract void release();

    /**
     * Used to handle message.
     */
    @Override
    abstract public void onHandleMessage(Message msg);


    @Override
    public float getDimension(int resId) {
        return getResources().getDimension(resId);
    }

    @SuppressLint("ShowToast")
    @Override
    public void showToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(message);
        mToast.show();
    }

    @SuppressLint("ShowToast")
    @Override
    public void showLongToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        }
        mToast.setText(message);
        mToast.show();
    }

    @Override
    public void showLoading() {
        showLoading("");
    }

    @Override
    public void showLoading(String message) {
        mLoading = ProgressDialog.show(this, "", message);
    }

    @Override
    public void hideLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }

    @Override
    public void toggleKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && this.getCurrentFocus() != null
                && this.getCurrentFocus().getWindowToken() != null) {
            imm.toggleSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                    0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void showKeyboard(View view) {
        if (view == null) return;
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive() && this.getCurrentFocus() != null
                && this.getCurrentFocus().getWindowToken() != null) {
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
