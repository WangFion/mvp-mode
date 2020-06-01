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
import com.wf.mvp.kotlin.impl.Impl

/**
 * MvpMode -> com.wf.mvp.kotlin.view -> IActivity
 * @Author: wf-pc
 * @Date: 2020-05-10 16:42
 * <p>
 * Mvp's v-layer basic no presenter Activity, used to implement common functions.
 */
abstract class NActivity : Activity(), Impl {

    /**
     * The instance object of UI thread handler.
     */
    private var mUiHandler: UiHandler? = null

    private var mToast: Toast? = null
    private var mLoading: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeCreate()
        super.onCreate(savedInstanceState)
        mUiHandler = UiHandler()
        mUiHandler?.attachRefs(this)
        setContentView(bindLayoutId())
        initView(intent)
        initListener()
        initData()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        mUiHandler?.detachRefs()
        mUiHandler = null
        release()
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
     * Used to handle message.
     */
    abstract override fun onHandleMessage(msg: Message)


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
            0, InputMethodManager.HIDE_NOT_ALWAYS
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