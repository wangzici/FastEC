package wzt.latte_core.ui.recyclerview;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * @author Tao
 * @date 2018/3/23
 * desc:
 */
public class BaseDecoration extends DividerItemDecoration {
    public static BaseDecoration create(@ColorInt int color,int size) {
        return new BaseDecoration(color, size);
    }

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }
}
