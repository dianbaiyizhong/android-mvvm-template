package com.zhenmei.template.android.ui.main;

import androidx.lifecycle.MutableLiveData;

import com.zhenmei.mvvmba.core.activity.BaseViewModel;
import com.zhenmei.mvvmba.core.activity.RxUtils;
import com.zhenmei.template.android.entity.WeatherEntity;
import com.zhenmei.template.android.response.CommonRespHandleSubscriber;
import com.zhenmei.template.android.utils.RxResponseUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class MainViewModel extends BaseViewModel<MainModel> {


    protected MutableLiveData<String> liveData = new MutableLiveData<>();

    public MutableLiveData<String> getResult() {
        return liveData;
    }

    public void getData() {


        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("city", "北京");

        model.getWeather(paramMap)
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxResponseUtils.exceptionTransformer())
                .doOnSubscribe((Consumer<Disposable>) disposable -> {
                    // 开始显示进度条
                })
                .subscribe(new CommonRespHandleSubscriber<WeatherEntity>(context) {
                    @Override
                    public void onNext(@NonNull WeatherEntity entity) {
                        liveData.postValue(entity.toString());
                    }
                });

    }
}
