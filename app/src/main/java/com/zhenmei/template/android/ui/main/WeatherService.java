package com.zhenmei.template.android.ui.main;


import com.zhenmei.template.android.entity.WeatherEntity;
import com.zhenmei.template.android.response.BaseResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherService {


    @GET("/weather_mini")
    Observable<BaseResponse<WeatherEntity>> getList(@QueryMap Map<String, String> map);


}
