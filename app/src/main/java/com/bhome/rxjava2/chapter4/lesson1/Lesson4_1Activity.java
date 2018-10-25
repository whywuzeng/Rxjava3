package com.bhome.rxjava2.chapter4.lesson1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bhome.rxjava2.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Function;


public class Lesson4_1Activity extends AppCompatActivity {

    private static final String TAG = "Lesson4_1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4_1);
//        findViewById(R.id.btn_rxjava1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Observable.
//                        create(new Observable.OnSubscribe<String>() {
//                            @Override
//                            public void call(Subscriber<? super String> subscriber) {
//                                if (!subscriber.isUnsubscribed()) {
//                                    subscriber.onNext("1");
//                                    subscriber.onNext("2");
//                                    subscriber.onCompleted();
//                                }
//                            }
//                        }).
//                        map(new Func1<String, Integer>() {
//                            @Override
//                            public Integer call(String s) {
//                                return Integer.parseInt(s);
//                            }
//                        }).
//                        subscribe(new Observer<Integer>() {
//                            @Override
//                            public void onCompleted() {
//                                System.out.println("onCompleted");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(Integer integer) {
//                                System.out.println("onNext:" + integer + ",integer instanceOf" + integer.getClass());
//                            }
//                        });
//            }
//        });

//        findViewById(R.id.btn_self_rxjava1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Caller.create(new Caller.OnCall<String>() {
//                    @Override
//                    public void Call(Receiver<String> stringReceiver) {
//                        stringReceiver.OnReceive("12");
//                    }
//                }).map(new com.bhome.rxjava2.Call.Func1<String, Integer>() {
//                    @Override
//                    public Integer Call(String s) {
//                        final Integer integer = Integer.valueOf(s);
//                        return integer;
//                    }
//                }).subscribe(new Receiver<Integer>() {
//                    @Override
//                    public void onError(Throwable t) {
//
//                    }
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void OnReceive(Integer integer) {
//                        Log.e(TAG, "OnReceive: integer.getClass(): "+integer.getClass() +"integer"+integer.toString());
//                    }
//                });
//            }
//        });

        findViewById(R.id.btn_self_rxjava2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.btn_rxjava2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Observable.
//                        create(new ObservableOnSubscribe<String>() {
//                            @Override
//                            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                                if (!e.isDisposed()) {
//                                    e.onNext("1");
//                                    e.onNext("2");
//                                    e.onComplete();
//                                }
//                            }
//                        }).
//                        map(new Function<String, Integer>() {
//                            @Override
//                            public Integer apply(String s) throws Exception {
//                                return Integer.parseInt(s);
//                            }
//                        }).
//                        subscribe(new Observer<Integer>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//                                System.out.println("onSubscribe");
//                            }
//
//                            @Override
//                            public void onNext(Integer value) {
//                                System.out.println("onNext:" + value);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                System.out.println("onComplete");
//                            }
//                        });

                Flowable.
                        create(new FlowableOnSubscribe<String>() {
                            @Override
                            public void subscribe(FlowableEmitter<String> e) throws Exception {
                                if (!e.isCancelled()) {
                                    e.onNext("1");
                                    e.onNext("2");
                                    e.onComplete();
                                }
                            }
                        }, BackpressureStrategy.DROP).
                        map(new Function<String, Integer>() {
                            @Override
                            public Integer apply(String s) throws Exception {
                                return Integer.parseInt(s);
                            }
                        }).
                        subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onSubscribe(Subscription s) {
                                s.request(Long.MAX_VALUE);
                                System.out.println("onSubscribe");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                System.out.println("onNext:" + integer);
                            }

                            @Override
                            public void onError(Throwable t) {

                            }

                            @Override
                            public void onComplete() {
                                System.out.println("onComplete");
                            }
                        });
            }
        });
    }
}
