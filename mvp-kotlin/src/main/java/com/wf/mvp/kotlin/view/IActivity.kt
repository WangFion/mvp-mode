package com.wf.mvp.kotlin.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.wf.mvp.kotlin.customize.UiHandler
import com.wf.mvp.kotlin.impl.Presenter
import com.wf.mvp.kotlin.impl.Ui
import com.wf.mvp.kotlin.presenter.DefaultIPresenter
import java.lang.reflect.ParameterizedType

/**
 * MvpMode -> com.wf.mvp.kotlin.view -> IActivity
 * @Author: wf-pc
 * @Date: 2020-05-10 16:42
 * <p>
 * Mvp's v-layer basic Activity, used to bind presenter and implement common functions.
 */
abstract class IActivity<P : Presenter> : Activity(), Ui {

    /**
     * The instance object of P layer.
     */
    protected var mPresenter: P? = null

    /**
     * The instance object of UI thread handler.
     */
    protected var mUiHandler: UiHandler? = null

    private var mToast: Toast? = null
    private var mLoading: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeCreate()
        super.onCreate(savedInstanceState)
        setContentView(bindLayoutId())
        initView(intent)
        attachPresenter()
        initListener()
        initData()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        detachPresenter()
        release()
    }


    /**
     * Binding P layer instance object.
     * Obtain the generic class type through reflection, and then instantiate the Presenter object.
     */
    @Suppress("UNCHECKED_CAST")
    override fun attachPresenter() {
        mUiHandler = UiHandler()
        mUiHandler?.attachRefs(this)
        mPresenter = try {
            val type = this.javaClass.genericSuperclass as ParameterizedType
            val tClass: Class<P> = type.actualTypeArguments[0] as Class<P>
            tClass.newInstance()
        } catch (e: Exception) {
            DefaultIPresenter() as P
        }
        mPresenter?.attachView(this, mUiHandler)
    }

    /**
     * Unbind the P layer instance object.
     */
    override fun detachPresenter() {
        mUiHandler?.detachRefs()
        mPresenter?.detachView()
        mUiHandler = null
        mPresenter = null
    }


    /**
     * Used to do something before Create.
     */
    protected open fun beforeCreate() {}

    /**
     * Used to bind layout files.
     */
    protected abstract fun bindLayoutId(): Int

    /**
     * Used to initialize view.
     */
    protected abstract fun initView(intent: Intent?)

    /**
     * Used to initialize listener.
     */
    protected abstract fun initListener()

    /**
     * Used to initialize data.
     */
    protected abstract fun initData()

    /**
     * Used to release resources.
     */
    protected abstract fun release()

    /**
     * It is not defined as an abstract function here,
     * it is recommended to put the message to the Presenter layer for processing.
     */
    override fun onHandleMessage(msg: Message) {}


    override fun runOnUiThread(block: () -> Unit) {
        mUiHandler?.post(block)
    }

    override fun getDimension(resId: Int): Float {
        return resources?.getDimension(resId) ?: 0f
    }

    @SuppressLint("ShowToast")
    override fun showToast(message: String) {
        if (mToast == null) {
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT)
        }
        mToast?.setText(message)
        mToast?.show()
    }

    @SuppressLint("ShowToast")
    override fun showLongToast(message: String) {
        if (mToast == null) {
            mToast = Toast.makeText(this, "", Toast.LENGTH_LONG)
        }
        mToast?.setText(message)
        mToast?.show()
    }

    override fun showLoading() {
        showLoading("")
    }

    override fun showLoading(message: String) {
        mLoading = ProgressDialog.show(this, "", message)
    }

    override fun hideLoading() {
        mLoading?.dismiss()
    }

    override fun toggleKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInputFromWindow(
            this.currentFocus?.windowToken,
            0,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    override fun showKeyboard(view: View) {
        view.requestFocus()
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    override fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(
            this.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}