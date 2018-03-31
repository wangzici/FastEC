package wzt.latte_ec.main.index.search;

import android.support.v7.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;

import java.util.List;

import wzt.latte_core.ui.recyclerview.MultipleFields;
import wzt.latte_core.ui.recyclerview.MultipleItemEntity;
import wzt.latte_core.ui.recyclerview.MultipleViewHolder;
import wzt.latte_ec.R;

/**
 * @author Tao
 * @date 2018/3/31
 * desc:
 */
public class SearchAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (entity.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
