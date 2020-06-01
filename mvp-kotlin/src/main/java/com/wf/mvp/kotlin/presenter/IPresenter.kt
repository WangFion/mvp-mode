package com.wf.mvp.kotlin.presenter

import android.content.res.Resources
import android.graphics.drawable.Drawable
import com.wf.mvp.kotlin.customize.UiHandler
import com.wf.mvp.kotlin.impl.Presenter
import com.wf.mvp.kotlin.impl.Ui

/**
 * MvpMode -> com.wf.mvp.kotlin.presenter -> Presenter
 * @Author: wf-pc
 * @Date: 2020-05-10 16:13
 * <p>
 * Mvp's p-layer basic Presenter, used to attach activity and implement common functions.
 */
abstract class IPresenter<V : Ui> : Presenter {

    /**
     * The instance object of V layer.
     */
    protected var mView: V? = null

    /**
     * The instance object of UI thread handler.
     */
    protected var mUiHandler: UiHandler? = null

    /**
     * Establish a reference relationship with the V layer.
     *
     * @param view The instance object of V layer, must be a subclass of Ui.
     * @param handler The instance object of UI thread handler.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <U : Ui> attachView(view: U, handler: UiHandler?) {
        mView = view as? V ?: throw TypeCastException("view is not an implementation class of Ui interface.")
        mUiHandler = handler
        mUiHandler?.attachRefs(this)
        init()
    }

    /**
     * Release references to layer V.
     */
    override fun detachView() {
        release()
        mUiHandler?.detachRefs()
        mUiHandler = null
        mView = null
    }

    /**
     * Only initialization code can be written here, no other operations can be performed.
     * For example, initialize the M layer instance object.
     */
    protected abstract fun init()

    /**
     * Used to release resources.
     */
    protected abstract fun release()


    override fun runOnUiThread(block: () -> Unit) {
        mView?.runOnUiThread(block)
    }

    override fun getResources(): Resources? {
        return mView?.getResources()
    }

    override fun getColor(resId: Int): Int {
        return mView?.getResources()?.getColor(resId) ?: 0
    }

    override fun getString(resId: Int): String {
        return mView?.getResources()?.getString(resId) ?: ""
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return mView?.getResources()?.getString(resId, *formatArgs) ?: ""
    }

    override fun getDimension(resId: Int): Float {
        return mView?.getResources()?.getDimension(resId) ?: 0f
    }

    override fun getDrawable(resId: Int): Drawable? {
        return mView?.getResources()?.getDrawable(resId)
    }


    override fun showToast(message: String) {
        mView?.showToast(message)
    }

    override fun showLongToast(message: String) {
        mView?.showLongToast(message)
    }

    override fun showLoading() {
        mView?.showLoading()
    }

    override fun showLoading(message: String) {
        mView?.showLoading(message)
    }

    override fun hideLoading() {
        mView?.hideLoading()
    }

    override fun toggleKeyboard() {
        mView?.toggleKeyboard()
    }

    override fun showKeyboard(view: android.view.View) {
        mView?.showKeyboard(view)
    }

    override fun hideKeyboard() {
        mView?.hideKeyboard()
    }

}
