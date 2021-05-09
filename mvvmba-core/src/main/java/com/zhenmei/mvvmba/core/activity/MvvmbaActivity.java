package com.zhenmei.mvvmba.core.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.ReflectUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MvvmbaActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends LifecycleActivity {
    protected VM viewModel;
    protected V viewDataBinding;

    public abstract int setLayout();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, setLayout());
        //利用 子类传递的 泛型参数实例化出absViewModel 对象。
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] arguments = type.getActualTypeArguments();
        if (arguments.length > 1) {
            Type argument = arguments[1];
            Class modelClazz = ((Class) argument).asSubclass(BaseViewModel.class);
            viewModel = (VM) ViewModelProviders.of(this).get(modelClazz);
            viewModel.injectLifecycleProvider(this);
            // 获取父类泛型，并实例化model注入
            Class entityClass = (Class) ((ParameterizedType) modelClazz.getGenericSuperclass()).getActualTypeArguments()[0];
            viewModel.injectModel(ReflectUtils.reflect(entityClass).newInstance().get());
        }

    }

}
