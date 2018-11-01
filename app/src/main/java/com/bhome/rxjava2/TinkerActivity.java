package com.bhome.rxjava2;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bhome.rxjava2.tinker.TinkerService;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2018-11-01.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2
 */

public class TinkerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TinkerActivity";

    private Button btnStartService;
    private Button btnStopService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinker);
        btnStartService=findViewById(R.id.btn_startservice);
        btnStopService=findViewById(R.id.btn_stopservice);
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        Log.e(TAG, "onCreate:渠道号 "+ getMetaString());
    }

    private String getMetaString() {
        try {
            final ApplicationInfo applicationInfo = this.getPackageManager().getApplicationInfo(this.getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
            return applicationInfo.metaData.getString("UMENG_CHANNEL");

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(this, TinkerService.class);
        switch (v.getId())
        {
            case R.id.btn_startservice:
                startService(intent);
                break;
            case R.id.btn_stopservice:
                stopService(intent);
                break;
                default:
                    break;
        }
    }
}
