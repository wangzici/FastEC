package wzt.latte_core.delegates.bottom;

import android.os.SystemClock;
import android.widget.Toast;

import wzt.latte_core.R;
import wzt.latte_core.app.Latte;
import wzt.latte_core.delegates.LatteDelegate;

/**
 * @author Tao
 * @date 2018/3/19
 * desc:
 */
public abstract class BottomItemDelegate extends LatteDelegate {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            getActivity().finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(getActivity(), "双击退出" + Latte.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
