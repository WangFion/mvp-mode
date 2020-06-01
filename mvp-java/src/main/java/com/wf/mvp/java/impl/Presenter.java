package com.wf.mvp.java.impl;

import com.wf.mvp.java.customize.UiHandler;

/**
 * MvpMode -> com.wf.mvp.java.imp -> IPresenter
 *
 * @Author: wf-pc
 * @Date: 2020-05-09 20:59
 * <p>
 * Mvp's p-layer specification, used to limit the interfaces that the presenter must implement.
 */
public interface Presenter<V extends Ui> extends Impl {

    /**
     * Establish a reference relationship with the V layer.
     *
     * @param view The instance object of V layer, must be a subclass of Ui.
     * @param handler The instance object of UI thread handler.
     */
    void attachView(V view, UiHandler handler);

    /**
     * Release references to layer V.
     */
    void detachView();

}
