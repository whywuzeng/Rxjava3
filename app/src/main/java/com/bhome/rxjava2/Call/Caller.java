package com.bhome.rxjava2.Call;

/**
 * 打电话的人
 * Created by wz on 2018/10/19.
 */

public class Caller<T> {

    private OnCall<T> soure;

    public static <T> Caller<T> create(OnCall<T> call){
        return new Caller<>(call);
    }

    public Caller(OnCall<T> call){
        this.soure=call;
    }

    public Calling subscribe(Receiver<T> receiver){
        soure.Call(receiver);
        return receiver;
    }

    //打电话的动作
    public  interface OnCall<T> extends Action<Receiver<T>> {

    }

}
