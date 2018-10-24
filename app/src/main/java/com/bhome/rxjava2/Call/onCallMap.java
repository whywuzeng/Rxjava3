package com.bhome.rxjava2.Call;

/**
 * Created by Administrator on 2018-10-24.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2.Call
 */

class onCallMap<T, R>  implements Caller.OnCall<R> {


    private final Func1<? super T, ? extends R> func1;
    private final Caller<T> tCaller;

    public onCallMap(Caller<T> tCaller, Func1<? super T, ? extends R> func1) {
        this.tCaller=tCaller;
         this.func1=func1;
    }


    @Override
    public void Call(Receiver<R> rReceiver) {
        final MapSubscriber<R, T> rtMapSubscriber = new MapSubscriber<>(func1, rReceiver);

        tCaller.subscribe(rtMapSubscriber);
    }

    static final class MapSubscriber<R,T> extends Receiver<T>{

        private final Func1<? super T, ? extends R> func1;
        private final Receiver<R> rReceiver;

        public MapSubscriber(Func1<? super T, ? extends R> func1, Receiver<R> rReceiver) {
            this.func1=func1;
            this.rReceiver=rReceiver;
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void OnReceive(T t) {
            final R r = func1.Call(t);
            rReceiver.OnReceive(r);
        }


    }
}
