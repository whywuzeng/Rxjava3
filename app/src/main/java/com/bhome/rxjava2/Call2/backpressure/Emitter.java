package com.bhome.rxjava2.Call2.backpressure;

/**
 * Created by Administrator on 2018-10-24.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2.Call2.backpressure
 */

public interface Emitter<T> {
    void onNext(T value);
    void onError();
    void onComplete();
}
