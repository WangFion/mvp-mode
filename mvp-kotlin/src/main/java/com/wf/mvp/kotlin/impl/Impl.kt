package com.wf.mvp.kotlin.impl

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Message
import android.view.View

/**
 * MvpMode -> com.wf.mvp.kotlin.imp -> Impl
 * @Author: wf-pc
 * @Date: 2020-05-10 15:15
 * <p>
 * The top-level interface, used to limit the interfaces that the view and presenter must implement.
 */
interface Impl {

    fun runOnUiThread(block: () -> Unit)

    fun onHandleMessage(msg: Message)

    fun getResources(): Resources?

    fun getColor(resId: Int): Int

    fun getString(resId: Int): String

    fun getString(resId: Int, vararg formatArgs: Any): String

    fun getDimension(resId: Int): Float

    fun getDrawable(resId: Int): Drawable?

    fun showToast(message: String)

    fun showLongToast(message: String)

    fun showLoading()

    fun showLoading(message: String)

    fun hideLoading()

    fun toggleKeyboard()

    fun showKeyboard(view: View)

    fun hideKeyboard()

}