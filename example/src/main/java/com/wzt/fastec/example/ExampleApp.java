package com.wzt.fastec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import wzt.latte_core.app.Latte;
import wzt.latte_ec.icon.FontEcModule;

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
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
    }
}
