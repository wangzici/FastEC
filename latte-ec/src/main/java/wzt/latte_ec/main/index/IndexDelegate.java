package wzt.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;
import wzt.latte_core.delegates.bottom.BottomItemDelegate;
import wzt.latte_core.ui.recyclerview.BaseDecoration;
import wzt.latte_core.ui.refresh.PagingBean;
import wzt.latte_core.ui.refresh.RefreshHandler;
import wzt.latte_core.ui.scanner.ScannerDelegate;
import wzt.latte_core.util.callback.CallBackManager;
import wzt.latte_core.util.callback.CallbackType;
import wzt.latte_core.util.callback.IGlobalCallback;
import wzt.latte_ec.R;
import wzt.latte_ec.R2;
import wzt.latte_ec.main.ECBottomDelegate;

/**
 * @author Tao
 * @date 2018/3/19
 * desc:
 */
public class IndexDelegate extends BottomItemDelegate {
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

    @OnClick(R2.id.icon_index_scan)
    public void onScanClick() {
        CallBackManager.getInstance().addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
            @Override
            public void executeCallback(@Nullable String args) {
                Toast.makeText(getContext(), args, Toast.LENGTH_LONG).show();
            }
        });
        getParentDelegate().start(new ScannerDelegate());
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRecyclerView();
        initRefreshLayout();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter(), new PagingBean());
        mRefreshHandler.firstPage("index.php");
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        BaseDecoration decoration = BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5);
        mRecyclerView.addItemDecoration(decoration);
        final ECBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    private void initRefreshLayout() {
        final int[] colors = new int[]{
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light};
        mRefreshLayout.setColorSchemeResources(colors);
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }
}
