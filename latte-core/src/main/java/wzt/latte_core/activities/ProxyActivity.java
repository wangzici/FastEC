package wzt.latte_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivity;
import wzt.latte_core.R;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.util.log.LatteLogger;

/**
 * @author Tao
 * @date 2018/2/26
 * desc:
 */
public abstract class ProxyActivity extends SupportActivity {
    /**
     * 用于返回根Fragment
     *
     * @return 返回根Fragment
     */
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if(savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LatteLogger.d("wzt","ProxyActivity.onDestroy");
        System.gc();
        System.runFinalization();
    }
}
