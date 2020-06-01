package com.wf.mvp.java.presenter;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.View;

import com.wf.mvp.java.customize.UiHandler;
import com.wf.mvp.java.impl.Presenter;
import com.wf.mvp.java.impl.Ui;

/**
 * MvpMode -> com.wf.mvp.java.presenter -> SuperPresenter
 *
 * @Author: wf-pc
 * @Date: 2020-05-09 21:02
 * <p>
 * Mvp's p-layer basic Presenter, used to attach activity and implement common functions.
 */
public abstract class IPresenter<V extends Ui> implements Presenter<V> {

    /**
     * The instance object of V layer.
     */
    protected V mView;

    /**
     * The instance object of UI thread handler.
     */
    protected UiHandler mUiHandler;

    /**
     * Establish a reference relationship with the V layer.
     *
     * @param view The instance object of V layer, must be a subclass of Ui.
     * @param handler The instance object of UI thread handler.
     */
    @Override
    public void attachView(V view, UiHandler handler) {
        mView = view;
        mUiHandler = handler;
        mUiHandler.attachRefs(this);
        init();
    }

    /**
     * Release references to layer V.
     */
    @Override
    public void detachView() {
        release();
        if (mUiHandler != null) {
            mUiHandler.detachRefs();
            mUiHandler = null;
        }
        if (mView != null) {
            mView = null;
        }
    }

    /**
     * Only initialization code can be written here, no other operations can be performed.
     * For example, initialize the M layer instance object.
     */
    protected abstract void init();

    /**
     * Used to release resources.
     */
    protected abstract void release();


    @Override
    public void runOnUiThread(Runnable runnable) {
        mView.runOnUiThread(runnable);
    }

    @Override
    public Resources getResources() {
        return mView.getResources();
    }

    @Override
    public int getColor(int resId) {
        return mView.getResources().getColor(resId);
    }

    @Override
    public String getString(int resId) {
        return mView.getResources().getString(resId);
    }

    @Override
    public String getString(int resId, Object... formatArgs) {
        return mView.getResources().getString(resId, formatArgs);
    }

    @Override
    public float getDimension(int resId) {
        return mView.getResources().getDimension(resId);
    }

    @Override
    public Drawable getDrawable(int resId) {
        return mView.getResources().getDrawable(resId);
    }


    @Override
    public void showToast(String message) {
        mView.showToast(message);
    }

    @Override
    public void showLongToast(String message) {
        mView.showLongToast(message);
    }

    @Override
    public void showLoading() {
        mView.showLoading();
    }

    @Override
    public void showLoading(String message) {
        mView.showLoading(message);
    }

    @Override
    public void hideLoading() {
        mView.hideLoading();
    }

    @Override
    public void toggleKeyboard() {
        mView.toggleKeyboard();
    }

    @Override
    public void showKeyboard(View view) {
        mView.showKeyboard(view);
    }

    @Override
    public void hideKeyboard() {
        mView.hideKeyboard();
    }

}
