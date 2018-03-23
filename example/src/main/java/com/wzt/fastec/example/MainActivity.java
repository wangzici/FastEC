package com.wzt.fastec.example;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import qiu.niorgai.StatusBarCompat;
import wzt.latte_core.activities.ProxyActivity;
import wzt.latte_core.app.ConfigType;
import wzt.latte_core.app.Latte;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_ec.launcher.ILauncherListener;
import wzt.latte_ec.launcher.LauncherDelegate;
import wzt.latte_ec.launcher.OnLauncherFinishTag;
import wzt.latte_ec.main.ECBottomDelegate;
import wzt.latte_ec.sign.ISignListener;
import wzt.latte_ec.sign.SignInDelegate;

/**
 * @author Tao
 */
public class MainActivity extends ProxyActivity implements ILauncherListener, ISignListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurations().put(ConfigType.ACTIVITY.name(), this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                getSupportDelegate().startWithPop(new ECBottomDelegate());
                break;
            case NOT_SIGNED:
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }
}
