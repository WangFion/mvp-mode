package com.wf.mvp.mode.java.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wf.mvp.java.view.IActivity;
import com.wf.mvp.mode.R;
import com.wf.mvp.mode.java.contract.JavaContract;
import com.wf.mvp.mode.java.presenter.JavaIPresenter;

/**
 * MvpMode -> com.wf.mvp.mode.java -> JavaActivity
 *
 * @Author: wf-pc
 * @Date: 2020-05-10 13:06
 */
public class JavaActivity extends IActivity<JavaIPresenter> implements JavaContract.View {

    private final String TAG = JavaActivity.class.getSimpleName();
    private Button mBtnSay;
    public TextView mTvInfo;
    private EditText mEtInfo;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_java;
    }

    @Override
    protected void initView(Intent intent) {
        mBtnSay = findViewById(R.id.btn_say);
        mTvInfo = findViewById(R.id.tv_info);
        mEtInfo = findViewById(R.id.et_info);
    }

    @Override
    protected void initListener() {
        mBtnSay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getInfo();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {
        mPresenter.initText(mPresenter.getClass().toString());
    }

    @Override
    protected void release() {

    }

    @Override
    public void setText(String text) {
        mTvInfo.setText(text);
    }

    @Override
    public void setTextColor(int color) {
        mTvInfo.setTextColor(color);
    }

}
