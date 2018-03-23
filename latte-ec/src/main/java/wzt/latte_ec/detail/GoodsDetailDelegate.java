package wzt.latte_ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_ec.R;

/**
 * @author Tao
 * @date 2018/3/23
 * desc:
 */
public class GoodsDetailDelegate extends LatteDelegate {

    public static GoodsDetailDelegate create() {
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
