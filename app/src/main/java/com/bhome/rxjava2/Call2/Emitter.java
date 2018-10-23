package com.bhome.rxjava2.Call2;

/**
 * Created by wz on 2018/10/19.
 */

public interface Emitter<T> {

    void OnNext(T value);

    void onError(Throwable t);

    void onComplete();

}
