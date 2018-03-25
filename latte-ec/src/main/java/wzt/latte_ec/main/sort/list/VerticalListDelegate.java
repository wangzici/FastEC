package wzt.latte_ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import wzt.latte_core.app.ConfigType;
import wzt.latte_core.app.Latte;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.net.RestClient;
import wzt.latte_core.net.callback.ISuccess;
import wzt.latte_core.util.log.LatteLogger;
import wzt.latte_ec.R;
import wzt.latte_ec.R2;
import wzt.latte_ec.main.sort.SortDelegate;

/**
 * @author Tao
 * @date 2018/3/24
 * desc:
 */
public class VerticalListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecycleView();
    }

    private void initRecycleView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        LatteLogger.d("onLazyInitView,url = " + Latte.getConfiguration(ConfigType.API_HOST.name()) + "sort_list.php");
        RestClient.builder()
                .url("sort_list.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        VerticalListConverter converter = new VerticalListConverter();
                        converter.setJsonData(response);
                        mRecyclerView.setAdapter(SortRecycleAdapter.create(converter.convert(), (SortDelegate) getParentDelegate()));
                    }
                })
                .build()
                .get();
    }
}
