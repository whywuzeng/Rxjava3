package com.bhome.rxjava2.Call2.backpressure;

/**
 * Created by Administrator on 2018-10-24.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2.Call2.backpressure
 */

public interface TelephonerOnSubscribe<T>  {
    void subscribe(TelephonerEmitter<T> emitter);
}
