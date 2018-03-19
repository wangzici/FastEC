package wzt.latte_core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;
import wzt.latte_core.R;
import wzt.latte_core.R2;
import wzt.latte_core.delegates.LatteDelegate;

/**
 * @author Tao
 * @date 2018/3/19
 * desc:
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> mItems = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();


    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = mCurrentDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }
        ItemBuilder builder = ItemBuilder.builder();
        mItems.putAll(setItems(builder));
        for (Map.Entry<BottomTabBean, BottomItemDelegate> entry : mItems.entrySet()) {
            TAB_BEANS.add(entry.getKey());
            ITEM_DELEGATES.add(entry.getValue());
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        int size = mItems.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView iconTextView = (IconTextView) item.getChildAt(0);
            final AppCompatTextView textView = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            iconTextView.setText(bean.getIcon());
            textView.setText(bean.getTitle());
            if (i == mIndexDelegate) {
                iconTextView.setTextColor(mClickedColor);
                textView.setTextColor(mClickedColor);
            }else {
                iconTextView.setTextColor(Color.GRAY);
                textView.setTextColor(Color.GRAY);
            }
        }
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container,mIndexDelegate,delegateArray);

    }

    private void resetColor() {
        int size = mBottomBar.getChildCount();
        for (int i = 0; i < size; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            IconTextView iconTextView = (IconTextView) item.getChildAt(0);
            AppCompatTextView textView = (AppCompatTextView) item.getChildAt(1);
            iconTextView.setTextColor(Color.GRAY);
            textView.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate = tag;
    }
}
