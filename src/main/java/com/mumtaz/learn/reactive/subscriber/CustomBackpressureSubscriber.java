package com.mumtaz.learn.reactive.subscriber;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class CustomBackpressureSubscriber<T> extends BaseSubscriber<T> {

    int consumed;
    final int limit = 5;

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        request(limit);
    }

    @Override
    protected void hookOnNext(T value) {
        // do business logic there
        System.out.println("value==============" + value + ":       " + consumed);
        consumed++;

        if (consumed == limit) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consumed = 0;

            request(limit);
        }
    }


}
