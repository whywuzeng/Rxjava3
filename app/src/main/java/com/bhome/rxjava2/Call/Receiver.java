package com.bhome.rxjava2.Call;

/**
 * Created by wz on 2018/10/19.
 */

public abstract class Receiver<T> implements Calling,Callee<T> {

    private volatile boolean isUnCall ;

    @Override
    public boolean isUnCalled() {
        return isUnCall;
    }

    @Override
    public void UnCall() {
        isUnCall=true;
    }
}
