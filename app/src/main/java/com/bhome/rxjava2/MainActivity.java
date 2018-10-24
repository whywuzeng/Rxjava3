package com.bhome.rxjava2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bhome.rxjava2.chapter3.lesson7.Lesson3_7Activity;
import com.bhome.rxjava2.chapter4.lesson1.Lesson4_1Activity;

public class MainActivity extends AppCompatActivity {

  private final static String TAG="MainActivity";
  Button button;
  Button btn_Lesson4_1Activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= findViewById(R.id.btn_toggle);
        btn_Lesson4_1Activity=findViewById(R.id.btn_Lesson4_1Activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lesson3_7Activity.class));
            }
        });

        btn_Lesson4_1Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lesson4_1Activity.class));
            }
        });
//        findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
//                                                        @Override
//                                                        public void onClick(View v) {
//                                                            Observable.create(new ObservableOnSubscribe<String>() {
//                                                                @Override
//                                                                public void subscribe(ObservableEmitter<String> e) throws Exception {
//                                                                    if (!e.isDisposed()) {
//                                                                        e.onNext("test");
//                                                                        e.onComplete();
//                                                                    }
//                                                                }
//                                                            }).subscribeOn(Schedulers.newThread())
//                                                                    .observeOn(Schedulers.newThread())
//                                                                    .subscribe(new Observer<String>() {
//                                                                @Override
//                                                                public void onSubscribe(Disposable d) {
//                                                                    Log.e(TAG, "onSubscribe: ");
//                                                                }
//
//                                                                @Override
//                                                                public void onNext(String value) {
//                                                                    Log.e(TAG, "onNext: " + value);
//                                                                }
//
//                                                                @Override
//                                                                public void onError(Throwable e) {
//                                                                    Log.e(TAG, "onError: ");
//                                                                }
//
//                                                                @Override
//                                                                public void onComplete() {
//                                                                    Log.e(TAG, "onComplete: ");
//                                                                }
//                                                            });
//                                                        }
//                                                    });

//        findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Flowable.create(new FlowableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(FlowableEmitter<String> e) throws Exception {
//                        if (!e.isCancelled()) {
//                            e.onNext("test");
//                            e.onComplete();
//                        }
//                    }
//                }, BackpressureStrategy.DROP)
//                        .subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        Log.e(TAG, "onSubscribe: ");
//                        s.request(Long.MAX_VALUE);
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.e(TAG, "onNext: " + s);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        Log.e(TAG, "onError: ");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e(TAG, "onComplete: ");
//                    }
//                });
//            }
//        });


//        findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Subscription test = Observable.create(new Observable.OnSubscribe<String>() {
//                    @Override
//                    public void call(Subscriber<? super String> subscriber) {
//                        if (!subscriber.isUnsubscribed()) {
//                            subscriber.onNext("test");
//                            subscriber.onCompleted();
//                        }
//                    }
//                }).subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//
//                    }
//                });
//            }
//        });

//        findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Caller.create(new Caller.OnCall<String>() {
//                    @Override
//                    public void Call(Receiver<String> stringReceiver) {
//                        if (!stringReceiver.isUnCalled()) {
//                            stringReceiver.OnReceive("123");
//                            stringReceiver.onCompleted();
//                        }
//                    }
//                }).subscribe(new Receiver<String>() {
//                    @Override
//                    public void onError(Throwable t) {
//                        Log.e(TAG, "onError: ");
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        Log.e(TAG, "onCompleted: ");
//                    }
//
//                    @Override
//                    public void OnReceive(String s) {
//                        Log.e(TAG, "OnReceive: " + s);
//                    }
//                });
//            }
//        });

//        findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Caller2.create(new CallerOnCall<String>() {
//                    @Override
//                    public void subscribe(CallerEmitter<String> emitter) {
//                        if (!emitter.isUnCalled())
//                        {
//                            emitter.OnNext("123");
//                            emitter.onComplete();
//                        }
//                    }
//                }).subscribe(new com.bhome.rxjava2.Call2.Receiver<String>() {
//                    @Override
//                    public void OnSubcribe() {
//                        Log.e(TAG, "OnSubcribe: " );
//                    }
//
//                    @Override
//                    public void OnNext(String value) {
//                        Log.e(TAG, "OnNext: "+value );
//                    }
//
//                    @Override
//                    public void OnComplete() {
//                        Log.e(TAG, "OnComplete: " );
//                    }
//                });
//
//            }
//        });



    }
}
