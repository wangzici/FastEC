package com.wzt.fastec.example;


import android.os.Bundle;
import android.support.annotation.Nullable;

import wzt.latte_core.activities.ProxyActivity;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_ec.launcher.LauncherDelegate;
import wzt.latte_ec.launcher.LauncherScrollDelegate;
import wzt.latte_ec.sign.SignInDelegate;
import wzt.latte_ec.sign.SignUpDelegate;

/**
 * @author Tao
 */
public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignInDelegate();
    }
}
