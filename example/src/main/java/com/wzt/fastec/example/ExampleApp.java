package com.wzt.fastec.example;

import android.app.Application;
import android.support.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wzt.fastec.example.event.TestEvent;

import cn.jpush.android.api.JPushInterface;
import me.yokeyword.fragmentation.Fragmentation;
import wzt.latte_core.app.Latte;
import wzt.latte_core.net.interceptors.DebugInterceptor;
import wzt.latte_core.util.callback.CallBackManager;
import wzt.latte_core.util.callback.CallbackType;
import wzt.latte_core.util.callback.IGlobalCallback;
import wzt.latte_ec.database.DatabaseManager;
import wzt.latte_ec.icon.FontEcModule;

/**
 * @author Tao
 * @date 2018/2/24
 * desc:
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(getApplicationContext())
                .withApiHost("http://192.168.0.3/RestServer/api/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("myAppId")
                .withWeChatAppSecret("myAppSecret")
                .withJavascriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .configure();
        initStetho();
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true)
                .install();
        DatabaseManager.getInstance().init(getApplicationContext());
        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());

        CallBackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.stopPush(getApplicationContext());
                        }
                    }
                });
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(getApplicationContext())
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(getApplicationContext()))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(getApplicationContext()))
                        .build()
        );
    }
}
