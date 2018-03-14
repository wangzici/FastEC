package wzt.latte_core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import wzt.latte_core.R;
import wzt.latte_core.util.dimen.DimenUtil;

/**
 * @author Tao
 * @date 2018/3/7
 * desc:
 */
public class LatteLoader {
    private static final int LOADER_SIZE_SCALE = 8;
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    public static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context,Enum<LoaderStyle> style) {
        showLoading(context, style.name());
    }

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.MyAppCompactDialogStyle);

        final AVLoadingIndicatorView loadingView = LoaderCreator.create(context, type);
        dialog.setContentView(loadingView);

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = DimenUtil.getScreenWidth() / LOADER_SIZE_SCALE;
            lp.height = DimenUtil.getScreenHeight() / LOADER_SIZE_SCALE;
            Log.i("wzt", "lp.width = " + lp.width + ";lp.height = " + lp.height);
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();
            }
        }
    }
}
