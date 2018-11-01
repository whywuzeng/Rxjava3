package com.bhome.rxjava2.tinker;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bhome.rxjava2.http3.RequestCenter;
import com.bhome.rxjava2.http3.listener.DisposeDataListener;
import com.bhome.rxjava2.http3.listener.DisposeDownloadListener;
import com.bhome.rxjava2.tinker.module.BasePatch;
import com.bhome.rxjava2.tinker.module.PatchInfo;

import java.io.File;

/**
 * Created by Administrator on 2018-11-01.
 * <p>
 * by author wz
 * 检测是否下载patch包  包括上传本应用的渠道名，然后去区分服务器的渠道patch包
 * //下载patch包后loadPatch加载
 * 下次启动程序时效果显示
 * android:exported
 * android:process=":string"  ":string"  与 "string"区别
 * <p>
 * com.bhome.rxjava2.tinker
 */

public class TinkerService extends Service {

    private static final String FILE_END=".apk";

    private static final String TAG = "TinkerService";
    private static final int UPDATE_PATCH = 0x21;
    private static final int DOWN_PATCH = 0x22;
    //服务器返回的更新信息
    private BasePatch basePatch;

    //patch文件夹
    private String mPatchFileDir;
    //patch文件路径
    private String mFilePatch;

    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */
    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: ");
        super.onCreate();
        init();
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_PATCH:
                    checkPatchInfo();
                    break;
                case DOWN_PATCH:
                     downloadFile();
                    break;
                default:
                    Log.e(TAG, "handleMessage: 出错");
                    break;
            }
        }
    };

    private void downloadFile() {
        final PatchInfo data = basePatch.data;
        mFilePatch = mPatchFileDir + data.versionName + FILE_END;
        RequestCenter.downloadFile(data.downloadUrl, this.mFilePatch, new DisposeDownloadListener() {
            @Override
            public void onProgress(int progrss) {
                Log.e(TAG, "onProgress: "+progrss);
            }

            @Override
            public void onSuccess(Object responseObj) {
                TinkerManager.loadPatch(TinkerService.this.mFilePatch);
            }

            @Override
            public void onFailure(Object reasonObj) {
                stopSelf();
            }
        });
    }

    /**
     * 检测是否需要更新patch
     */
    private void checkPatchInfo() {
        RequestCenter.requestPatchUpdateInfo(new DisposeDataListener() {

            @Override
            public void onSuccess(Object responseObj) {
                basePatch = (BasePatch) responseObj;
                handler.sendEmptyMessage(DOWN_PATCH);
            }

            @Override
            public void onFailure(Object reasonObj) {
                stopSelf();
            }
        });
    }

    private void init(){
        mPatchFileDir= getExternalCacheDir().getAbsolutePath()+"/tpatch/";
        final File patchFileDir = new File(mPatchFileDir);
        try {
            if (patchFileDir==null||!patchFileDir.exists())
            {
                //是否应用一生成 ，这个目录都自己建立了
                patchFileDir.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stopSelf();
        }
    }

    /**
     * 每次通过startService()方法启动Service时都会被回调。
     *
     * @param intent
     * @param flags   表示启动请求时是否有额外数据，可选值有 0，START_FLAG_REDELIVERY，START_FLAG_RETRY，0代表没有，
     * @param startId
     * @return 它有三种可选值， START_STICKY，START_NOT_STICKY，START_REDELIVER_INTENT
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        //这里是下载 patch 。下载完load 会关闭应用。重启会有什么影响
        handler.sendEmptyMessage(UPDATE_PATCH);
        return START_NOT_STICKY;
    }

    /**
     * 服务销毁
     */
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
