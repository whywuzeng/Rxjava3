package com.bhome.rxjava2;

import android.app.Application;

/**
 * Created by Administrator on 2018-10-26.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2
 */

public class RxJava3App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndFixPatchManager.getInstance().initPatchManager(this);
    }
}
