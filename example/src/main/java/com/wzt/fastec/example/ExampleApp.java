package com.wzt.fastec.example;

import android.app.Application;

import wzt.latte_core.app.Latte;

/**
 *
 * @author Tao
 * @date 2018/2/24
 * desc:
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(getApplicationContext())
                .withApiHost("http://127.0.0.1")
                .configure();
    }
}
