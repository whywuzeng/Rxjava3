package com.bhome.rxjava2.Call2;

/**
 * Created by wz on 2018/10/19.
 */

public interface Receiver<T> {
    void OnSubcribe();
    void OnNext(T value);
    void OnComplete();
}
