package wzt.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author Tao
 * @date 2018/2/26
 * desc:
 */
public abstract class BaseDelegate extends Fragment implements ISupportFragment {
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
        Log.i("wzt", "onCreateView");

        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }
        Log.i("wzt", "rootView = " + rootView);
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
        }
    }

    @Override
    public SupportFragmentDelegate getSupportDelegate() {
        return new SupportFragmentDelegate(this);
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return null;
    }

    @Override
    public void enqueueAction(Runnable runnable) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onEnterAnimationEnd(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onSupportVisible() {

    }

    @Override
    public void onSupportInvisible() {

    }

    @Override
    public boolean isSupportVisible() {
        return false;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return null;
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return null;
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {

    }

    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {

    }

    @Override
    public void onNewBundle(Bundle args) {

    }

    @Override
    public void putNewBundle(Bundle newBundle) {

    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }
}
