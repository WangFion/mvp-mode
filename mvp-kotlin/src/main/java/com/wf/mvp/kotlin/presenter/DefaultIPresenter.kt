package com.wf.mvp.kotlin.presenter

import android.os.Message
import com.wf.mvp.kotlin.impl.Presenter
import com.wf.mvp.kotlin.impl.Ui

/**
 * MvpMode -> com.wf.mvp.kotlin.presenter -> DefaultPresenter
 * @Author: wf-pc
 * @Date: 2020-05-10 16:51
 * <p>
 * The default Presenter is to satisfy the situation where Presenter is not needed.
 */
class DefaultIPresenter : IPresenter<Ui>() {

    override fun init() {
    }

    override fun release() {
    }

    override fun onHandleMessage(msg: Message) {
    }

}
