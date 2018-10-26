package com.bhome.rxjava2;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

/**
 * Created by Administrator on 2018-10-26.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2
 */

public class AndFixPatchManager {

    private String appVersion;

    private final static class HODLE{
        private final static AndFixPatchManager MANAGER = new AndFixPatchManager();
    }

    public static AndFixPatchManager getInstance(){
        return HODLE.MANAGER;
    }

    //pachmanager
    private  PatchManager manager;

    //init pachManager
    public void initPatchManager(Context context){
        manager= new PatchManager(context);
        appVersion= getVersionName(context);
        manager.init(appVersion);
        manager.loadPatch();
    }
    //得到版本号
    private String getVersionName(Context context){
        String localVersion ="1.0.0";

        final PackageManager pm = context.getPackageManager();
        try {
            final PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
             localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    //add path
    public void addPatch(String path){
        if (manager!=null) {
            try {
                manager.addPatch(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
