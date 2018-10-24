package com.bhome.rxjava2.Call2.backpressure;

/**
 * Created by Administrator on 2018-10-24.
 * <p>
 * by author wz
 * 发射数据的状态
 * <p>
 * com.bhome.rxjava2.Call2.backpressure
 */

public interface TelephonerEmitter<T>  extends Emitter{
    void setDisposable(Disposable s);
    long requested();
    void requested(long n);
}
