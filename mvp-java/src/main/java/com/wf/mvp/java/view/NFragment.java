package com.wf.mvp.java.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.wf.mvp.java.customize.UiHandler;
import com.wf.mvp.java.impl.Impl;

/**
 * MvpMode -> com.wf.mvp.java.view -> NFragment
 *
 * @Author: wf-pc
 * @Date: 2020-05-27 18:19
 * <p>
 * Mvp's v-layer basic no presenter Fragment, used to implement common functions.
 */
abstract public class NFragment extends Fragment implements Impl {

    /**
     * The instance object of UI thread handler.
     */
    protected UiHandler mUiHandler;

    private Toast mToast;
    private ProgressDialog mLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        beforeCreate();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(bindLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUiHandler = new UiHandler();
        mUiHandler.attachRefs(this);
        Intent intent = getActivity() == null ? new Intent() : getActivity().getIntent();
        initView(view, intent);
        initListener();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUiHandler != null) {
            mUiHandler.detachRefs();
            mUiHandler = null;
        }
        release();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
    protected abstract void initView(View layout, Intent intent);

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
    public void runOnUiThread(Runnable runnable) {
        if (getActivity() == null) return;
        getActivity().runOnUiThread(runnable);
    }

    @Override
    public int getColor(int resId) {
        if (getActivity() == null) return 0;
        return getActivity().getResources().getColor(resId);
    }

    @Override
    public float getDimension(int resId) {
        if (getActivity() == null) return 0;
        return getActivity().getResources().getDimension(resId);
    }

    @Override
    public Drawable getDrawable(int resId) {
        if (getActivity() == null) return null;
        return getActivity().getResources().getDrawable(resId);
    }


    @SuppressLint("ShowToast")
    @Override
    public void showToast(String message) {
        if (getActivity() == null) return;
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(message);
        mToast.show();
    }

    @SuppressLint("ShowToast")
    @Override
    public void showLongToast(String message) {
        if (getActivity() == null) return;
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), "", Toast.LENGTH_LONG);
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
        if (getActivity() == null) return;
        mLoading = ProgressDialog.show(getActivity(), "", message);
    }

    @Override
    public void hideLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }

    @Override
    public void toggleKeyboard() {
        if (getActivity() == null) return;
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && getActivity().getCurrentFocus() != null
                && getActivity().getCurrentFocus().getWindowToken() != null) {
            imm.toggleSoftInputFromWindow(
                    getActivity().getCurrentFocus().getWindowToken(),
                    0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void showKeyboard(View view) {
        if (getActivity() == null || view == null) return;
        view.requestFocus();
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    @Override
    public void hideKeyboard() {
        if (getActivity() == null) return;
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive() && getActivity().getCurrentFocus() != null
                && getActivity().getCurrentFocus().getWindowToken() != null) {
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
