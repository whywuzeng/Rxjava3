package com.bhome.rxjava2.Call2;

/**
 * Created by wz on 2018/10/19.
 */

public class Caller2Create<T> extends Caller2<T> {

    private final CallerOnCall call;

    public Caller2Create(CallerOnCall call) {
        this.call = call;
    }

    public void subscribeActual(Receiver<T> receiver)
    {
        CreateEmitter<T> tCreateEmitter = new CreateEmitter<>(receiver);

        call.subscribe(tCreateEmitter);

    }

    static final class CreateEmitter<T> extends CallerEmitter<T> implements Calling{

        public final Receiver<T> receiver;

        public CreateEmitter(Receiver<T> receiver){
            this.receiver = receiver;
        }

        @Override
        public void OnNext(T value) {
            if (!isUnCalled())
            {
                receiver.OnNext(value);
            }
        }

        @Override
        public void onError(Throwable t) {
        }

        @Override
        public void onComplete() {
            if (!isUnCalled())
            {
                receiver.OnComplete();
            }
        }
    }
}
