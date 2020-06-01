package com.wf.mvp.mode;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.wf.mvp.java.view.IActivity;
import com.wf.mvp.mode.java.view.JavaActivity;
import com.wf.mvp.mode.kotlin.view.KotlinIActivity;
import com.wf.mvp.mode.kotlin.view.KotlinNoActivity;

/**
 * MvpMode -> com.wf.mvp.mode -> MainActivity
 *
 * @Author: wf-pc
 * @Date: 2020-05-10 12:59
 */
public class MainActivity extends IActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private Button mBtnJava;
    private Button mBtnJavaNp;
    private Button mBtnKotlin;
    private Button mBtnKotlinNp;

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Intent intent) {
        mBtnJava = findViewById(R.id.btn_java);
        mBtnJavaNp = findViewById(R.id.btn_java_np);
        mBtnKotlin = findViewById(R.id.btn_kotlin);
        mBtnKotlinNp = findViewById(R.id.btn_kotlin_np);
    }

    @Override
    protected void initListener() {
        mBtnJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JavaActivity.class));
            }
        });

        mBtnJavaNp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, JavaActivity.class));
            }
        });

        mBtnKotlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KotlinIActivity.class));
            }
        });

        mBtnKotlinNp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KotlinNoActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        showToast("presenter=" + mPresenter.getClass());
    }

    @Override
    protected void release() {

    }
}
