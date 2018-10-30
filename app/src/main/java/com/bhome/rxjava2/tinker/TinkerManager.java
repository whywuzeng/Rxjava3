package com.bhome.rxjava2.tinker;

import android.content.Context;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * Created by Administrator on 2018-10-30.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2.tinker
 */

public class TinkerManager {

    private static boolean isInstall =false;

    private static ApplicationLike mApplicationLike;

    //完成tinker初始化 标志位
    public static void initTinkerStall(ApplicationLike like){
        mApplicationLike=like;
        if (isInstall)
            return;
        TinkerInstaller.install(like);
        isInstall=true;
    }

    //完成load
    public static void loadPatch(String path){
        if (Tinker.isTinkerInstalled())
        {
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),path);
        }
    }

    public static Context getApplicationContext(){
        if (mApplicationLike!=null)
        {
            return mApplicationLike.getApplication().getApplicationContext();
        }
        return null;
    }

}
