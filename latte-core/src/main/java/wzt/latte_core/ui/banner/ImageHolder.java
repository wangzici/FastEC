package wzt.latte_core.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * @author Tao
 * @date 2018/3/22
 * desc:
 */
public class ImageHolder implements Holder<String>{
    private AppCompatImageView mAppCompatImageView;
    @Override
    public View createView(Context context) {
        mAppCompatImageView = new AppCompatImageView(context);
        return mAppCompatImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide
                .with(context)
                .load(data)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mAppCompatImageView);
    }
}
