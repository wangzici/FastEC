package wzt.latte_core.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import wzt.latte_core.app.ConfigType;
import wzt.latte_core.app.Latte;

/**
 * @author Tao
 * @date 2018/3/7
 * desc:
 */
public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }
}
