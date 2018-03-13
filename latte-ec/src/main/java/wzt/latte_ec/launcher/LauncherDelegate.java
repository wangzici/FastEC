package wzt.latte_ec.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import java.text.MessageFormat;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.util.storage.LattePreference;
import wzt.latte_core.util.timer.BaseTimerTask;
import wzt.latte_core.util.timer.ITimerListener;
import wzt.latte_ec.R;
import wzt.latte_ec.R2;

/**
 * @author Tao
 * @date 2018/3/11
 * desc:
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener{
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;

    private ScheduledThreadPoolExecutor mTimer;
    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        //TODO
        mTimer.shutdown();
        checkIsShowScroll();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {
        //这里尝试使用ScheduledThreadPoolExecutor代替了Timer，并且实现了一个ThreadFactor，用于对生成的Thread来命名
        mTimer = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread(r, LatteDelegate.class.getName());
            }
        });
        mTimer.scheduleAtFixedRate(new BaseTimerTask(this), 0, 1, TimeUnit.SECONDS);
    }

    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            Log.i("wzt", "getSupportDelegate = " + getSupportDelegate());
            getSupportDelegate().start(new LauncherScrollDelegate(),SINGLETASK);
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                mCount--;
                if (mCount < 0) {
                    mTimer.shutdown();
                    checkIsShowScroll();
                }
            }
        });
    }
}
