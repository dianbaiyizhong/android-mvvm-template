package com.zhenmei.template.android.response;

import java.io.Serializable;

/**
 * 网络请求结果 基类
 */

public class BaseResponse<T> implements Serializable {
    public int status;
    public String desc;

    public T data;
    public Object exData;


    public boolean isSuccess() {
        return status == 1000;
    }
}

