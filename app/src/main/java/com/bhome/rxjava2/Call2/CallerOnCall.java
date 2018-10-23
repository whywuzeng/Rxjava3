package com.bhome.rxjava2.Call2;

/**
 * Created by wz on 2018/10/19.
 *  打电话的动作
 */

public interface CallerOnCall<T> {

    void subscribe(CallerEmitter<T> emitter);
}
