package com.wf.mvp.mode.java.presenter;

import android.os.Message;

import com.wf.mvp.java.presenter.IPresenter;
import com.wf.mvp.mode.R;
import com.wf.mvp.mode.java.contract.JavaContract;
import com.wf.mvp.mode.java.view.JavaActivity;

/**
 * MvpMode -> com.wf.mvp.mode.java.presenter -> JavaPresenter
 *
 * @Author: wf-pc
 * @Date: 2020-05-10 13:09
 */
public class JavaIPresenter extends IPresenter<JavaActivity> implements JavaContract.Presenter {

    private StringBuilder mText = new StringBuilder();

    @Override
    protected void init() {

    }

    @Override
    protected void release() {

    }

    @Override
    public void onHandleMessage(Message msg) {
        if (msg.what == 1001) {
            showLoading("1001");
        }
    }

    @Override
    public void initText(String text) {
        mText.append(mView.getClass()).append("\n");
        mText.append(text).append("\n\n");
        mView.setText(mText.toString());
    }

    @Override
    public void getInfo() {
        mText.append(getResources().toString()).append("\n");
        mText.append(getString(R.string.app_name)).append("\n");
        mText.append(getString(R.string.app_hello, "java")).append("\n");
        mText.append("dimen_720p=").append(getDimension(R.dimen.dimen_720p)).append("\n");
        mText.append(getDrawable(R.mipmap.ic_launcher).toString()).append("\n");

        mView.setText(mText.toString());
        mView.setTextColor(getColor(R.color.colorPrimary));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //测试
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("Hello Java !");
                    }
                });

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mUiHandler.sendEmptyMessage(1001);

                mUiHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoading();
                        toggleKeyboard();
                    }
                }, 3000);

                mUiHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toggleKeyboard();
                    }
                }, 6000);
            }
        }).start();
    }
}
