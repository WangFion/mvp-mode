package com.wf.mvp.java.presenter;

import android.os.Message;

/**
 * MvpMode -> com.wf.mvp.java.presenter -> DefaultPresenter
 *
 * @Author: wf-pc
 * @Date: 2020-05-09 21:08
 * <p>
 * The default Presenter is to satisfy the situation where Presenter is not needed.
 */
public class DefaultIPresenter extends IPresenter {

    @Override
    protected void init() {

    }

    @Override
    protected void release() {

    }

    @Override
    public void onHandleMessage(Message msg) {

    }

}
