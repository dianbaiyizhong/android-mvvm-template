package com.zhenmei.mvvmba.core.activity;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.trello.rxlifecycle4.LifecycleProvider;
import com.zhenmei.mvvmba.network.BaseModel;

import java.lang.ref.WeakReference;

public  class BaseViewModel<M extends BaseModel> extends ViewModel {
    protected M model;

    protected Context context;

    //弱引用持有
    private WeakReference<LifecycleProvider> lifecycle;


    public void injectModel(M model){
        this.model = model;
    }


    public void injectContext(Context context){

        this.context = context;
    }

    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    public void injectLifecycleProvider(LifecycleProvider lifecycle) {
        this.lifecycle = new WeakReference<>(lifecycle);
    }

    public LifecycleProvider getLifecycleProvider() {
        return lifecycle.get();
    }


}
