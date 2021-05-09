package com.zhenmei.template.android.ui.main;

import android.os.Bundle;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.zhenmei.mvvmba.core.activity.MvvmbaActivity;
import com.zhenmei.mvvmba.network.app.ManBaNetBuilder;
import com.zhenmei.template.android.R;
import com.zhenmei.template.android.databinding.ActivityMainLayoutBinding;

public class MainActivity extends MvvmbaActivity<ActivityMainLayoutBinding, MainViewModel> {


    @Override
    public int setLayout() {
        return R.layout.activity_main_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.addLogAdapter(new AndroidLogAdapter());
        ManBaNetBuilder.init(this)
                .withApiHost("http://wthrcdn.etouch.cn/")
                .configure();

        super.onCreate(savedInstanceState);
        viewModel.getData();
        viewModel.getResult().observe(this, response -> {
            viewDataBinding.textView.setText(response);
        });
    }


}