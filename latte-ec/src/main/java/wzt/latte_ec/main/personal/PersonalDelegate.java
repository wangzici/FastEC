package wzt.latte_ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import wzt.latte_core.delegates.bottom.BottomItemDelegate;
import wzt.latte_ec.R;
import wzt.latte_ec.R2;
import wzt.latte_ec.main.personal.list.ListAdapter;
import wzt.latte_ec.main.personal.list.ListBean;
import wzt.latte_ec.main.personal.list.ListItemType;

/**
 * @author Tao
 * @date 2018/3/28
 * desc:
 */
public class PersonalDelegate extends BottomItemDelegate{
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                //.setDelegate(new AddressDelegate())
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                //.setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        //mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));
    }
}
