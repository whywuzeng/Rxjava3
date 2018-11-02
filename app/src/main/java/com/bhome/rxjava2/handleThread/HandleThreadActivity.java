package com.bhome.rxjava2.handleThread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.bhome.rxjava2.R;

/**
 * Created by Administrator on 2018-11-02.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2.handleThread
 */

public class HandleThreadActivity extends AppCompatActivity {
    private static final String TAG = "HandleThreadActivity";
    private TextView tvMain;

    private HandlerThread mHandlerThread;

    //子线程中handler
    private Handler mThreadHandler;

    //主线程中Handler
    private Handler mMainThreadHandler;

    //以防退出界面后Handler还在执行
     private boolean isUpdateInfo;
     //用以表示该handler的常熟
     private static final int MSG_UPDATE_INFO = 0x110;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handle_thread_layout);
        tvMain=(TextView) findViewById(R.id.textView11);

        initThread();
    }

    private void initThread() {
        mHandlerThread=new HandlerThread("check_message_gogo");
        //这里线程开始
        mHandlerThread.start();

        mThreadHandler =new Handler(mHandlerThread.getLooper());
        mMainThreadHandler =new Handler(getMainLooper());
        mThreadHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //这个回调会在 HandlerThread 内部的线程里调用.相当于在另一个线程
                super.handleMessage(msg);
                update();
                if (isUpdateInfo)
                {
                    mThreadHandler.sendEmptyMessage(MSG_UPDATE_INFO);
                }
            }
        };


    }

    private void update() {

        try {
            Thread.sleep(2000);

            mMainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    String result="时间没过2秒就刷新下";
                    result= String.valueOf(Math.random());
                    Log.e(TAG, "run: "+result);
                    tvMain.setText(result);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        isUpdateInfo=true;
        mThreadHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isUpdateInfo=false;
        mThreadHandler.removeMessages(MSG_UPDATE_INFO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
    }
}
