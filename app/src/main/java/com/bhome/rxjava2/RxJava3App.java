package com.bhome.rxjava2;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.bhome.rxjava2.tinker.TinkerManager;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by Administrator on 2018-10-26.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2
 */
@DefaultLifeCycle(application = ".MyTinkerApplication",
                    flags = ShareConstants.TINKER_ENABLE_ALL,
                    loadVerifyFlag = false)
public class RxJava3App extends ApplicationLike {

    public RxJava3App(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        TinkerManager.initTinkerStall(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        AndFixPatchManager.getInstance().initPatchManager(this);

    }
}
