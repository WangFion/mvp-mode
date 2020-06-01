package com.wf.mvp.kotlin.impl

import com.wf.mvp.kotlin.customize.UiHandler

/**
 * MvpMode -> com.wf.mvp.kotlin.impl -> IPresenter
 * @Author: wf-pc
 * @Date: 2020-05-10 15:24
 * <p>
 * Mvp's p-layer specification, used to limit the interfaces that the presenter must implement.
 */
interface Presenter : Impl {

    /**
     * Establish a reference relationship with the V layer.
     *
     * @param view The instance object of V layer, must be a subclass of Ui.
     * @param handler The instance object of UI thread handler.
     */
    fun <U: Ui> attachView(view: U, handler: UiHandler?)

    /**
     * Release references to layer V.
     */
    fun detachView()

}