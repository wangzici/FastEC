package wzt.latte_ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import wzt.latte_core.app.Latte;
import wzt.latte_core.ui.recyclerview.MultipleFields;
import wzt.latte_core.ui.recyclerview.MultipleItemEntity;
import wzt.latte_core.util.log.LatteLogger;
import wzt.latte_ec.R;

/**
 * @author Tao
 * @date 2018/3/26
 * desc:
 */
public class ShopCartAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, BaseViewHolder> {
    private boolean mIsSelectedAll = false;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(BaseViewHolder holder, final MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);
                //取出所有控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);

                entity.setField(ShopCartItemFields.POSITION, holder.getAdapterPosition());
                //在左侧勾勾渲染之前改变全选与否状态
                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                if (isSelected) {
                    iconIsSelected.setTextColor((ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main)));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加左侧勾勾点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor
                                    (ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
