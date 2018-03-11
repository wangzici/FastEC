package com.wzt.fastec.example;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import me.yokeyword.fragmentation.ISupportFragment;
import wzt.latte_core.activities.ProxyActivity;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_ec.launcher.LauncherDeegate;

/**
 * @author Tao
 */
public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("wzt", "MainActivity onCreate");
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDeegate();
    }
}
