package com.zhenmei.mvvmba.network;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BaseModel {
    public BaseModel() {
    }

    protected static Map<String, String> bean2Map(Object o) {
        Gson gson = new Gson();

        Map<String, String> map = gson.fromJson(gson.toJson(o), new TypeToken<Map<String, String>>() {
        }.getType());
        if (map == null) {
            return Collections.emptyMap();
        } else {
            return map;
        }
    }

    protected static RequestBody generateRequestBody(Object object) {
        Gson gson = new Gson();
        String obj = gson.toJson(object);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj);
        return body;
    }

    protected static Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }


    protected <T> Observable<T> observe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected <T> T apiService(Class<T> service) {

        return RetrofitServiceManager.getInstance().create(service);
    }

}

