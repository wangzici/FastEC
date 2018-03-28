package wzt.latte_ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_ec.R;

/**
 * @author Tao
 * @date 2018/3/28
 * desc:
 */
public class NameDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
