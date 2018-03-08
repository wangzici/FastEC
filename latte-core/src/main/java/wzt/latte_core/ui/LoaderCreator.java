package wzt.latte_core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

import wzt.latte_core.R;

/**
 * 用于创建AVLoadingIndicatorView
 *
 * @author Tao
 * @date 2018/3/7
 * desc:
 */
final class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(Context context, String type) {
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        Indicator indicator = LOADING_MAP.get(type);
        if (indicator == null) {
            indicator = getIndicator(type);
        }
        Log.i("wzt", "indicator = " + indicator.toString());
        avLoadingIndicatorView.setIndicator(indicator);
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder stringBuilder = new StringBuilder();
        if (!name.contains(".")) {
            stringBuilder
                    .append(AVLoadingIndicatorView.class.getPackage().getName())
                    .append(".indicators")
                    .append(".");
        }
        stringBuilder.append(name);
        try {
            Class<?> customIndicator = Class.forName(stringBuilder.toString());
            Indicator indicator = (Indicator) customIndicator.newInstance();
            LOADING_MAP.put(name, indicator);
            return indicator;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
