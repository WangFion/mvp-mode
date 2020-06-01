package com.wf.mvp.kotlin.customize

import android.os.Handler
import android.os.Message
import com.wf.mvp.kotlin.impl.Impl

/**
 * MvpMode -> com.wf.mvp.kotlin.customize -> UiHandler
 * @Author: wf-pc
 * @Date: 2020-05-10 15:11
 */
class UiHandler: Handler(){

    private var mRefsList = ArrayList<Impl>()

    fun attachRefs(refs: Impl){
        mRefsList.add(refs)
    }

    fun detachRefs(){
        removeCallbacksAndMessages(null)
        mRefsList.clear()
    }

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        for (refs in mRefsList){
            refs.onHandleMessage(msg)
        }
    }
}