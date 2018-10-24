package com.bhome.rxjava2.chapter3.lesson7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bhome.rxjava2.Call2.backpressure.Subscriber;
import com.bhome.rxjava2.Call2.backpressure.Telephoner;
import com.bhome.rxjava2.Call2.backpressure.TelephonerEmitter;
import com.bhome.rxjava2.Call2.backpressure.TelephonerOnSubscribe;
import com.bhome.rxjava2.R;


public class Lesson3_7Activity extends AppCompatActivity {

    private static final String TAG = "Lesson3_7Activity";
    private Button async_allocated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3_7);
        async_allocated = findViewById(R.id.async_allocated);
        findViewById(R.id.async).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Telephoner.create(new TelephonerOnSubscribe<String>() {

                    @Override
                    public void subscribe(TelephonerEmitter<String> emitter) {
                        emitter.onNext("1");
                        emitter.onNext("2");
                        emitter.onNext("3");
                        emitter.onNext("4");
                    }
                }).subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(TelephonerEmitter<String> emitter) {
                        emitter.requested(2);
                    }

                    @Override
                    public void onNext(String value) {
                        Log.e(TAG, "onNext: " + value);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }

                    @Override
                    public void onError() {
                        Log.e(TAG, "onError: ");
                    }
                });
            }
        });

//        async_allocated.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Observable.create(new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<String> e) throws Exception {
//                        int i = 0;
//                        while (true) {
//                            i++;
//                            e.onNext(String.valueOf(i));
//                        }
//                    }
//                }).subscribeOn(Schedulers.newThread())
//                        .observeOn(Schedulers.newThread())
//                        .subscribe(new Observer<String>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(String value) {
//                                try {
//                                    Thread.sleep(5000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                System.out.println(value);
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
//            }
//        });

//        async_allocated.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Flowable.create(new FlowableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(FlowableEmitter<String> e) throws Exception {
//                        for (int i=0;i<=129;i++)
//                        {
//                            e.onNext(String.valueOf(i));
//                            Log.e(TAG, "subscribe: 未处理的消息还有多少"+e.requested() );
//                        }
//                        e.onComplete();
//                    }
//                }, BackpressureStrategy.MISSING)
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(Schedulers.newThread())
//                        .subscribe(new org.reactivestreams.Subscriber<String>() {
//
//                            @Override
//                            public void onSubscribe(Subscription s) {
//                                s.request(Long.MAX_VALUE);
//                            }
//
//                            @Override
//                            public void onNext(String s) {
////                                try {
////                                    Thread.sleep(50);
////                                } catch (InterruptedException e) {
////                                    e.printStackTrace();
////                                }
//                                Log.e(TAG, "onNext: "+s );
//                            }
//
//                            @Override
//                            public void onError(Throwable t) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                Log.e(TAG, "onComplete: 接收完成" );
//                            }
//                        });
//            }
//        });
    }
}
