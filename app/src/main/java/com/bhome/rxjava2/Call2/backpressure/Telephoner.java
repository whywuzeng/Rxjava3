package com.bhome.rxjava2.Call2.backpressure;

/**
 * Created by Administrator on 2018-10-24.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2.Call2.backpressure
 */

public abstract class Telephoner {

    public static <T> Telephoner create(TelephonerOnSubscribe<T> subscribe){
        return new TelephonerCreate<>(subscribe);
    }

    public void subscribe(Subscriber subscriber){
        subscribeActual(subscriber);
    }

    protected abstract void subscribeActual(Subscriber subscribe);
}
