package com.wf.mvp.mode.kotlin.view

import android.content.Intent
import android.os.Message
import com.wf.mvp.kotlin.view.NActivity
import com.wf.mvp.mode.R

/**
 * MvpMode -> com.wf.mvp.mode.kotlin.view -> KotlinNonActivity
 * @Author: wf-pc
 * @Date: 2020-05-11 20:33
 */
class KotlinNoActivity : NActivity() {

    override fun bindLayoutId(): Int {
        return R.layout.activity_kotlin_no_presenter;
    }

    override fun initView(intent: Intent?) {
    }

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun release() {
    }

    override fun onHandleMessage(msg: Message) {
    }

}