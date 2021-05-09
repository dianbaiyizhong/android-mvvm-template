package com.zhenmei.template.android.response;

import android.content.Context;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class CommonRespHandleSubscriber<T> implements Observer<T> {
    private Context context;

    public CommonRespHandleSubscriber(Context context) {
        this.context = context;
    }


    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }
}
