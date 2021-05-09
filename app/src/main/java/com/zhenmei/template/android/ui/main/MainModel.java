package com.zhenmei.template.android.ui.main;

import com.zhenmei.mvvmba.network.BaseModel;
import com.zhenmei.template.android.ui.main.WeatherService;

import java.util.Map;
import io.reactivex.rxjava3.core.Observable;

public class MainModel extends BaseModel {
    public Observable getWeather(Map<String, String> map) {
        return apiService(WeatherService.class).getList(bean2Map(map));
    }
}
