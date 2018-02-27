package com.wzt.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import wzt.latte_core.delegates.LatteDelegate;

/**
 * @author Tao
 * @date 2018/2/26
 * desc:
 */
public class ExampleDelegate extends LatteDelegate{

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
