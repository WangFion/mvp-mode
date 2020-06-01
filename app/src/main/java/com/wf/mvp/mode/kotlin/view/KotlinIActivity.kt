package com.wf.mvp.mode.kotlin.view

import android.annotation.SuppressLint
import android.content.Intent
import com.wf.mvp.kotlin.view.IActivity
import com.wf.mvp.mode.R
import com.wf.mvp.mode.kotlin.contract.KotlinContract
import com.wf.mvp.mode.kotlin.presenter.KotlinIPresenter
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinIActivity : IActivity<KotlinIPresenter>(), KotlinContract.View {

    override fun bindLayoutId(): Int {
        return R.layout.activity_kotlin
    }

    override fun initView(intent: Intent?) {

    }

    override fun initListener() {
        btn_kotlin.setOnClickListener {
            mPresenter?.getInfo()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        mPresenter?.initText("${mPresenter?.javaClass}")
    }

    override fun release() {
    }

    override fun setText(text: String?) {
        tv_kotlin.text = text ?: "null"
    }

    override fun setTextColor(color: Int) {
        tv_kotlin.setTextColor(color)
    }

}
