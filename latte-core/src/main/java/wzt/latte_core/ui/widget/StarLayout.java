package wzt.latte_core.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * @author Tao
 * @date 2018/3/30
 * desc:
 */
public class StarLayout extends LinearLayoutCompat implements View.OnClickListener {
    private static final CharSequence ICON_SELECT = "{fa-star}";
    private static final CharSequence ICON_UN_SELECT = "{fa-star-o}";
    private static final int STAR_TOTAL_COUNT = 5;
    private final ArrayList<IconTextView> STARS = new ArrayList<>();
    private int mSelectedNum;

    public StarLayout(Context context) {
        //这里调用到三个参数的原因是，这样的话只用在StarView里调用init方法即可
        this(context, null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        //注意：这里在之前犯了个错误，attrs没有写到第二个参数里，导致ButterKnife一直提示找不到对应id的StarLayout
        this(context, attrs, 0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStarIcon();
    }

    public int getStarCount() {
        return mSelectedNum + 1;
    }

    private void initStarIcon() {
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            final IconTextView iconTextView = new IconTextView(getContext());

            //注意此处是自己new一个LayoutParams，而不是直接从iconTextView里面取
            final LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            iconTextView.setLayoutParams(lp);

            iconTextView.setGravity(Gravity.CENTER);
            iconTextView.setText(ICON_SELECT);
            iconTextView.setTextColor(Color.RED);
            iconTextView.setTag(i);
            iconTextView.setOnClickListener(this);
            STARS.add(iconTextView);
            addView(iconTextView);
        }
        mSelectedNum = STAR_TOTAL_COUNT;
    }

    private void selectStar(int position) {
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            final IconTextView iconTextView = STARS.get(i);
            if (i <= position) {
                iconTextView.setText(ICON_SELECT);
                iconTextView.setTextColor(Color.RED);
            } else {
                iconTextView.setText(ICON_UN_SELECT);
                iconTextView.setTextColor(Color.GRAY);
            }
        }
        mSelectedNum = position;

    }

    @Override
    public void onClick(View v) {
        final int position = (int) v.getTag();
        selectStar(position);
    }
}
