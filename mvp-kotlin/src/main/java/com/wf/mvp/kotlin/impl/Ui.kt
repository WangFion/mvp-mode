package com.wf.mvp.kotlin.impl

/**
 * MvpMode -> com.wf.mvp.kotlin.impl -> IView
 * @Author: wf-pc
 * @Date: 2020-05-10 15:26
 * <p>
 * Mvp's v-layer specification, used to limit the interfaces that the view must implement.
 */
interface Ui : Impl {

    /**
     * Binding P layer instance object.
     * Obtain the generic class type through reflection, and then instantiate the Presenter object.
     */
    fun attachPresenter()

    /**
     * Unbind the P layer instance object.
     */
    fun detachPresenter()

}