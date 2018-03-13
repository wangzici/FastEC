package wzt.latte_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.ui.launcher.LauncherHolderCreator;
import wzt.latte_core.util.storage.LattePreference;
import wzt.latte_ec.R;

/**
 * @author Tao
 * @date 2018/3/12
 * desc:
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener{
    private ConvenientBanner<Integer> mConvenientBanner;
    private final ArrayList<Integer> INTEGERS = new ArrayList<>();

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                //Indicator的默认位置已经居中
                //.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onItemClick(int position) {
        LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
    }
}
