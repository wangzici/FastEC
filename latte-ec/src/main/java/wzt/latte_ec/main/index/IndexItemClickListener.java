package wzt.latte_ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.ui.recyclerview.MultipleFields;
import wzt.latte_core.ui.recyclerview.MultipleItemEntity;
import wzt.latte_ec.detail.GoodsDetailDelegate;

/**
 * @author Tao
 * @date 2018/3/23
 * desc:
 */
public class IndexItemClickListener extends SimpleClickListener {
    private final LatteDelegate DELEGATE;

    private IndexItemClickListener(LatteDelegate delegate) {
        DELEGATE = delegate;
    }

    public static IndexItemClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
