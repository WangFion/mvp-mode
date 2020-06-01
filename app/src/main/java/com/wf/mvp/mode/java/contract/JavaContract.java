package com.wf.mvp.mode.java.contract;

/**
 * MvpMode -> com.wf.mvp.mode.java.contract -> JavaContract
 *
 * @Author: wf-pc
 * @Date: 2020-05-10 13:08
 */
public interface JavaContract {

    interface View {
        void setText(String text);
        void setTextColor(int color);
    }

    interface Presenter {
        void initText(String text);
        void getInfo();
    }
}
