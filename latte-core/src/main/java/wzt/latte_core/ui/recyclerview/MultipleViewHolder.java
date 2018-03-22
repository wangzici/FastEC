package wzt.latte_core.ui.recyclerview;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @author Tao
 * @date 2018/3/22
 * desc:
 */
public class MultipleViewHolder extends BaseViewHolder{

    private MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
