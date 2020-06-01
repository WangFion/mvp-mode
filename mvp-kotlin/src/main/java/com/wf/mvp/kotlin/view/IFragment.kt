package com.wf.mvp.kotlin.view

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wf.mvp.kotlin.customize.UiHandler
import com.wf.mvp.kotlin.impl.Presenter
import com.wf.mvp.kotlin.impl.Ui
import com.wf.mvp.kotlin.presenter.DefaultIPresenter
import java.lang.reflect.ParameterizedType

/**
 * MvpMode -> com.wf.mvp.kotlin.view -> IFragment
 * @Author: wf-pc
 * @Date: 2020-05-18 21:18
 * <p>
 * Mvp's v-layer basic Fragment, used to bind presenter and implement common functions.
 */
abstract class IFragment<P : Presenter> : Fragment(), Ui {

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(bindLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = activity?.intent ?: Intent()
        initView(view, intent)
        attachPresenter()
        initListener()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detachPresenter()
        release()
    }

    override fun onDestroy() {
        super.onDestroy()
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
    protected abstract fun initView(layout: View, intent: Intent);

    /**
     * Used to initialize listener.
     */
    protected abstract fun initListener();

    /**
     * Used to initialize data.
     */
    protected abstract fun initData();

    /**
     * Used to release resources.
     */
    protected abstract fun release();

    /**
     * It is not defined as an abstract function here,
     * it is recommended to put the message to the Presenter layer for processing.
     */
    override fun onHandleMessage(msg: Message) {}


    override fun runOnUiThread(block: () -> Unit) {
        mUiHandler?.post(block)
    }

    override fun getDimension(resId: Int): Float {
        return resources.getDimension(resId)
    }

    @SuppressLint("ShowToast")
    override fun showToast(message: String) {
        if (activity != null) {
            if (mToast == null) {
                mToast = Toast.makeText(activity, "", Toast.LENGTH_SHORT)
            }
            mToast?.setText(message)
            mToast?.show()
        }
    }

    @SuppressLint("ShowToast")
    override fun showLongToast(message: String) {
        if (activity != null) {
            if (mToast == null) {
                mToast = Toast.makeText(activity, "", Toast.LENGTH_LONG)
            }
            mToast?.setText(message)
            mToast?.show()
        }
    }

    override fun showLoading() {
        showLoading("")
    }

    override fun showLoading(message: String) {
        if (activity != null) {
            mLoading = ProgressDialog.show(activity, "", message)
        }
    }

    override fun hideLoading() {
        mLoading?.dismiss()
    }

    override fun toggleKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInputFromWindow(
            activity?.currentFocus?.windowToken,
            0,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    override fun showKeyboard(view: View) {
        view.requestFocus()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    override fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(
            activity?.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}