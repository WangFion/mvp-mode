package com.wf.mvp.java.customize;

import android.os.Handler;
import android.os.Message;

import com.wf.mvp.java.impl.Impl;

import java.util.ArrayList;
import java.util.List;

/**
 * MvpMode -> com.wf.mvp.java.customize -> UiHandler
 *
 * @Author: wf-pc
 * @Date: 2020-05-10 13:56
 * <p>
 * This is the main thread handler, and the contexts are all soft references to avoid memory leaks.
 */
public class UiHandler extends Handler {

    private List<Impl> mRefsList = new ArrayList<>();

    public void attachRefs(Impl refs) {
        mRefsList.add(refs);
    }

    public void detachRefs() {
        removeCallbacksAndMessages(null);
        mRefsList.clear();
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        for (Impl refs : mRefsList) {
            refs.onHandleMessage(msg);
        }
    }
}
