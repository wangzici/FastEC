package wzt.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import wzt.latte_core.app.Latte;
import wzt.latte_core.delegates.bottom.BottomItemDelegate;
import wzt.latte_core.ui.refresh.RefreshHandler;
import wzt.latte_ec.R;
import wzt.latte_ec.R2;

/**
 * @author Tao
 * @date 2018/3/19
 * desc:
 */
public class IndexDelegate extends BottomItemDelegate{
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler;

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void initRefreshLayout() {
        final int[] colors = new int[]{
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light};
        mRefreshLayout.setColorSchemeResources(colors);
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
        mRefreshHandler = new RefreshHandler(mRefreshLayout);
    }
}
