package wzt.latte_ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import wzt.latte_core.delegates.bottom.BottomItemDelegate;
import wzt.latte_core.net.RestClient;
import wzt.latte_core.net.callback.ISuccess;
import wzt.latte_core.ui.recyclerview.MultipleItemEntity;
import wzt.latte_ec.R;
import wzt.latte_ec.R2;

/**
 * @author Tao
 * @date 2018/3/26
 * desc:
 */
public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {
    private ShopCartAdapter mAdapter;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mAdapter.setIsSelectedAll(true);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            mIconSelectAll.setTag(1);
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            mIconSelectAll.setTag(0);
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        List<MultipleItemEntity> data = mAdapter.getData();
        int dataSize = data.size();
        for (int i = dataSize; i > 0; i--) {
            MultipleItemEntity entity = data.get(i - 1);
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if(isSelected){
                final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
                mAdapter.remove(entityPosition);
            }
        }
        mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());

    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final List<MultipleItemEntity> data = new ShopCartDataConverter().setJsonData(response).convert();
        mAdapter = new ShopCartAdapter(data);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
