package wzt.latte_ec.main.sort;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import wzt.latte_core.delegates.bottom.BottomItemDelegate;
import wzt.latte_core.util.log.LatteLogger;
import wzt.latte_ec.R;
import wzt.latte_ec.main.sort.content.ContentDelegate;
import wzt.latte_ec.main.sort.list.VerticalListDelegate;

/**
 * @author Tao
 * @date 2018/3/19
 * desc:
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
