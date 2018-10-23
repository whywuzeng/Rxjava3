package com.bhome.rxjava2.Call2;

/**
 * Created by wz on 2018/10/19.
 */

public abstract class Caller2<T> {

    public static <T> Caller2 create(CallerOnCall<T> call)
    {
        return new Caller2Create(call);
    }

    public final void subscribe(Receiver<T> receiver)
    {
        subscribeActual(receiver);
    }

    public abstract void subscribeActual(Receiver<T> receiver);
}
