package com.bhome.rxjava2.Call2.backpressure;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2018-10-24.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2.Call2.backpressure
 */

public class TelephonerCreate<T> extends Telephoner{

    private TelephonerOnSubscribe subscribe;

    public TelephonerCreate(TelephonerOnSubscribe subscribe) {
        this.subscribe = subscribe;
    }


    @Override
    protected void subscribeActual(Subscriber subscribe) {
        final DropEmitter<Object> emitter = new DropEmitter<>(subscribe);
        subscribe.onSubscribe(emitter);
        this.subscribe.subscribe(emitter);
    }

    private static class DropEmitter<T> extends AtomicLong implements TelephonerEmitter<T>{

        private Subscriber subscriber;

        public DropEmitter(Subscriber subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void onNext(Object value) {
            if (get()!=0)
            {
                subscriber.onNext(value);
                BackpressureHelp.setProduce(this,1);
            }
        }

        @Override
        public void onError() {
            subscriber.onError();
        }

        @Override
        public void onComplete() {
            subscriber.onComplete();
        }

        @Override
        public void setDisposable(Disposable s) {

        }

        @Override
        public long requested() {

            return get();
        }

        @Override
        public void requested(long n) {
            BackpressureHelp.add(this,n);
        }
    }
}
