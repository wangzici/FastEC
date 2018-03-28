package wzt.latte_ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.net.RestClient;
import wzt.latte_core.net.callback.ISuccess;
import wzt.latte_core.ui.recyclerview.MultipleItemEntity;
import wzt.latte_ec.R;
import wzt.latte_ec.R2;

/**
 * @author Tao
 * @date 2018/3/28
 * desc:
 */
public class OrderListDelegate extends LatteDelegate{
    private String mType = null;
    public static final String ORDER_TYPE = "ORDER_TYPE";

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getArguments();
        mType = data.getString(ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("order_list.php")
                .params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                        //mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));
                    }
                })
                .build()
                .get();
    }
}
