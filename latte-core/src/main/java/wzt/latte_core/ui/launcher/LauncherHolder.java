package wzt.latte_core.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * @author Tao
 * @date 2018/3/12
 * desc:
 */
public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mAppCompatImageView;

    @Override
    public View createView(Context context) {
        mAppCompatImageView = new AppCompatImageView((context));
        return mAppCompatImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mAppCompatImageView.setBackgroundResource(data);
    }
}
