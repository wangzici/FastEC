package wzt.latte_core.util.callback;

import android.support.annotation.Nullable;

/**
 * @author Tao
 * @date 2018/3/29
 * desc:
 */
public interface IGlobalCallback<T> {
    void executeCallback(@Nullable T args);
}
