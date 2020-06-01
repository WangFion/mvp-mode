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
import com.wf.mvp.java.impl.Ui;
import com.wf.mvp.java.presenter.DefaultIPresenter;
import com.wf.mvp.java.presenter.IPresenter;

import java.lang.reflect.ParameterizedType;

/**
 * MvpMode -> com.wf.mvp.java.view -> IActivity
 *
 * @Author: wf-pc
 * @Date: 2020-05-09 21:11
 * <p>
 * Mvp's v-layer basic Activity, used to bind presenter and implement common functions.
 */
public abstract class IActivity<P extends IPresenter> extends Activity implements Ui {

    /**
     * The instance object of P layer.
     */
    protected P mPresenter;

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
        setContentView(bindLayoutId());
        initView(getIntent());
        attachPresenter();
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
        detachPresenter();
        release();
    }


    /**
     * Binding P layer instance object.
     * Obtain the generic class type through reflection, and then instantiate the Presenter object.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void attachPresenter() {
        mUiHandler = new UiHandler();
        mUiHandler.attachRefs(this);
        try {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<P> tClass = (Class<P>) type.getActualTypeArguments()[0];
            mPresenter = tClass.newInstance();
        } catch (Exception e) {
            mPresenter = (P) new DefaultIPresenter();
        }
        mPresenter.attachView(this, mUiHandler);
    }

    /**
     * Unbind the P layer instance object.
     */
    @Override
    public void detachPresenter() {
        if (mUiHandler != null) {
            mUiHandler.detachRefs();
            mUiHandler = null;
        }
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }


    /**
     * Used to do something before Create.
     */
    protected void beforeCreate() {}

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
     * It is not defined as an abstract function here,
     * it is recommended to put the message to the Presenter layer for processing.
     */
    @Override
    public void onHandleMessage(Message msg) {}


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
