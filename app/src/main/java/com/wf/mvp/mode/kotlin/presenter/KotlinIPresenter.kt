package com.wf.mvp.mode.kotlin.presenter

import android.os.Message
import com.wf.mvp.kotlin.presenter.IPresenter
import com.wf.mvp.mode.R
import com.wf.mvp.mode.kotlin.contract.KotlinContract
import com.wf.mvp.mode.kotlin.view.KotlinIActivity
import java.lang.StringBuilder

/**
 * MvpMode -> com.wf.mvp.mode -> MainPresenter
 *
 * @Author: wf-pc
 * @Date: 2020-05-09 22:06
 */
class KotlinIPresenter : IPresenter<KotlinIActivity>(), KotlinContract.Presenter {

    private var mText: StringBuilder = StringBuilder()

    override fun init() {
    }

    override fun release() {

    }

    override fun onHandleMessage(msg: Message) {
        if (msg.what == 1002) {
            showLoading("1002")
        }
    }

    override fun initText(text: String?) {
        mText.clear()
        mText.append(mView?.javaClass).append("\n")
        mText.append(text).append("\n\n")
        mView?.setText(mText.toString())
    }

    override fun getInfo() {
        mText.append(getResources().toString()).append("\n")
        mText.append(getString(R.string.app_name)).append("\n")
        mText.append(getString(R.string.app_hello, "kotlin")).append("\n")
        mText.append("dimen_720p=${getDimension(R.dimen.dimen_720p)}").append("\n")
        mText.append(getDrawable(R.mipmap.ic_launcher).toString()).append("\n")

        mView?.setText(mText.toString())
        mView?.setTextColor(getColor(R.color.colorPrimary))

        Thread(Runnable {
            try {
                Thread.sleep(3000);
            } catch (e: InterruptedException) {
                e.printStackTrace();
            }

            runOnUiThread {
                showToast("Hello Kotlin !");
            }

            try {
                Thread.sleep(3000);
            } catch (e: InterruptedException) {
                e.printStackTrace();
            }

            mUiHandler?.sendEmptyMessage(1002);

            mUiHandler?.postDelayed({
                hideLoading();
                toggleKeyboard();
            }, 3000)

            mUiHandler?.postDelayed({
                toggleKeyboard();
            }, 6000)

        }).start()
    }
}