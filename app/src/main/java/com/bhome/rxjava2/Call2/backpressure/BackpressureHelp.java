package com.bhome.rxjava2.Call2.backpressure;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2018-10-24.
 * <p>
 * by author wz
 * <p>
 * com.bhome.rxjava2.Call2.backpressure
 */

public class BackpressureHelp {

    public static void add(AtomicLong aLong,long n){
         long r = aLong.get();
        if (n == Long.MAX_VALUE)
            return;

        long u=r+n;
        if (u<0L)
        {
            u=Long.MAX_VALUE;
        }
        aLong.compareAndSet(r,u);
    }

    public static void setProduce(AtomicLong aLong,long n){
         long l = aLong.get();
         if (l == Long.MAX_VALUE)
             return;
         long u = l - n;
         if (u<0L)
         {
             u=Long.MAX_VALUE;
         }
         aLong.compareAndSet(l,u);
    }
}
