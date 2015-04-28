package com.example.apuser.mymicroblogging.domain.interactor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by apuser on 4/27/15.
 */
public abstract class UseCase {
    private Subscription subscription = Subscriptions.empty();

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @SuppressWarnings("unchecked")
    public void execute(Subscriber UseCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribe(UseCaseSubscriber);
    }

    protected abstract Observable buildUseCaseObservable();
}
