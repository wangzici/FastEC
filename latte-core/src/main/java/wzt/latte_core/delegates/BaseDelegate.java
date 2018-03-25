package wzt.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import wzt.latte_core.activities.ProxyActivity;

/**
 * @author Tao
 * @date 2018/2/26
 * desc:
 */
public abstract class BaseDelegate extends SupportFragment {
    /**
     * 返回layout的id或者RootView
     *
     * @return 返回layout的id或者RootView
     */
    public abstract Object setLayout();


    /**
     * 在onBindView中对控件进行操作
     *
     * @param savedInstanceState 界面缓存的数据
     * @param rootView           根View
     */
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);


    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        Object layout = setLayout();
        if (layout instanceof Integer) {
            rootView = inflater.inflate((Integer) layout, container, false);
        } else if (layout instanceof View) {
            rootView = (View) layout;
        }
        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            //LauncherDelegate会在进入登陆页面后，会因为未知原因，在onDestory后重新调用其onCreate与onDestory方法，会导致进入登陆页面后点击返回，出现异常的情况
            //目前暂时采取把mUnbinder在unbind之后置为空的方式解决
            mUnbinder = null;
        }
    }

    public ProxyActivity getProxyActivity() {
        return (ProxyActivity) getActivity();
    }
}
