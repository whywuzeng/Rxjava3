package com.bhome.rxjava2.scheme;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2018-11-02.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2
 */

public class AppNativeActivity extends AppCompatActivity{

    private static final String TAG = "AppNativeActivity";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Uri data = getIntent().getData();
        if (data!=null)
        {
            final List<String> pathSegments = data.getPathSegments();
            final String uriQuery = data.getQuery();

            Log.e(TAG, "onCreate: uriQuery :"+uriQuery );
            if (pathSegments!=null && pathSegments.size()>0)
            {
                for (String item :
                        pathSegments) {
                    Log.e(TAG, "onCreate: pathSegments -> item"+item );
                }
            }
        }
    }
}
