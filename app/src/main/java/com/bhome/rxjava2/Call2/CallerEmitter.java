package com.bhome.rxjava2.Call2;

/**
 * Created by wz on 2018/10/19.
 */

public abstract class CallerEmitter<T> implements Emitter<T>,Calling {

    private volatile boolean isUnCall;

    @Override
    public boolean isUnCalled() {
        return isUnCall;
    }

    @Override
    public void UnCall() {
        isUnCall=true;
    }
}
