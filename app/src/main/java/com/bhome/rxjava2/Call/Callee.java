package com.bhome.rxjava2.Call;

/**
 * Created by wz on 2018/10/19.
 * 接电话的人
 */

public interface Callee<T> {
    void onError(Throwable t);

    void onCompleted();

    void OnReceive(T t);
}
