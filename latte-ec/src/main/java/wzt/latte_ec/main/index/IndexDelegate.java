package wzt.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import wzt.latte_core.delegates.bottom.BottomItemDelegate;
import wzt.latte_ec.R;

/**
 * @author Tao
 * @date 2018/3/19
 * desc:
 */
public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
