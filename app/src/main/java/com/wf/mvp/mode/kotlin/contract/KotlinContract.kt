package com.wf.mvp.mode.kotlin.contract

/**
 * MvpMode -> com.wf.mvp.mode.kotlin.contract -> KotlinContract
 *
 * @Author: wf-pc
 * @Date: 2020-05-10 17:55
 */
interface KotlinContract {
    interface View {
        fun setText(text: String?)
        fun setTextColor(color: Int)
    }

    interface Presenter {
        fun initText(text: String?);
        fun getInfo()
    }
}