package com.wf.mvp.java.impl;

/**
 * MvpMode -> com.wf.mvp.java.imp -> IView
 *
 * @Author: wf-pc
 * @Date: 2020-05-09 20:53
 * <p>
 * Mvp's v-layer specification, used to limit the interfaces that the view must implement.
 */
public interface Ui extends Impl {

    /**
     * Binding P layer instance object.
     * Obtain the generic class type through reflection, and then instantiate the Presenter object.
     */
    void attachPresenter();

    /**
     * Unbind the P layer instance object.
     */
    void detachPresenter();

}
