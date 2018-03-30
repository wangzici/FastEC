package wzt.latte_core.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import wzt.latte_core.R;
import wzt.latte_core.util.log.LatteLogger;

/**
 * @author Tao
 * @date 2018/3/30
 * desc:
 */
public class AutoPhotoLayout extends LinearLayoutCompat {
    private static final String ICON_TEXT = "{fa-plus}";

    private final int mMaxNum;
    private final int mMaxLineNum;
    private final int mImageMargin;
    private final float mIconSize;

    private IconTextView mIconAdd;
    private LayoutParams mParams;

    /**
     * desc:由于onMeasure会调用多次，但是我们mIconAdd的LayoutParams的设置只需要一次，所以这里新增一个标志位
     */
    private boolean mIsOnceInitOnMeasure = false;

    private ArrayList<View> mLineViews = new ArrayList<>();
    private final ArrayList<Integer> LINE_HEIGHT = new ArrayList<>();
    private final ArrayList<ArrayList<View>> ALL_VIEWS = new ArrayList<>();

    public AutoPhotoLayout(Context context) {
        this(context, null);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        @SuppressLint("CustomViewStyleable") final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.camera_flow_layout);
        mMaxNum = typedArray.getInt(R.styleable.camera_flow_layout_max_count, 1);
        mMaxLineNum = typedArray.getInt(R.styleable.camera_flow_layout_line_count, 3);
        mImageMargin = typedArray.getInt(R.styleable.camera_flow_layout_item_margin, 0);
        mIconSize = typedArray.getDimension(R.styleable.camera_flow_layout_icon_size, 20);
        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int size = ALL_VIEWS.size();
        int left = getPaddingLeft();
        int top = getPaddingTop();
        for (int i = 0; i < size; i++) {
            mLineViews = ALL_VIEWS.get(i);
            final int lineSize = mLineViews.size();
            LatteLogger.d("wzt", "lineSize = " + lineSize);
            for (int j = 0; j < lineSize; j++) {
                View child = mLineViews.get(j);
                if (child.getVisibility() == GONE) {
                    //这里千万不能忘，当child为gone的时候，不进行处理
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                final int measuredWidth = child.getMeasuredWidth();
                final int measuredHeight = child.getMinimumHeight();

                LatteLogger.d("wzt", "measuredWidth = " + measuredWidth);
                LatteLogger.d("wzt", "measuredHeight = " + measuredHeight);

                final int lc = left + lp.leftMargin;
                final int tc = top + lp.topMargin;
                final int rc = lc + measuredWidth;
                final int bc = tc + measuredHeight;
                LatteLogger.d("wzt", "lc = " + lc);
                LatteLogger.d("wzt", "tc = " + tc);
                LatteLogger.d("wzt", "rc = " + rc);
                LatteLogger.d("wzt", "bc = " + bc);
                child.layout(lc, tc, rc, bc);
                //记得加上自定义的间隔属性
                left = rc + lp.rightMargin + mImageMargin;
            }
            //每一行结束后，重置left值
            left = getPaddingLeft();
            //每一行结束后，都加上这一行的高度
            final int lineHeight = LINE_HEIGHT.get(i);
            top += lineHeight;
        }
        mIconAdd.setLayoutParams(mParams);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ALL_VIEWS.clear();
        mLineViews.clear();
        LINE_HEIGHT.clear();

        final int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        final int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        final int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        //计算出来的在wrap_content下的宽度和高度
        int width = 0;
        int height = 0;
        //在计算过程中，记录每一行的宽度和高度
        int lineWidth = 0;
        int lineHeight = 0;
        //得到内部元素个数
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            //在计算之前，要先测量子View的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            //注意：childWidth除了测量的宽度意外，还不能忘记margin的值，并且是直接读取属性，而不是调用get方法
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            //此处是判断是否需要换行，注意sizeWith加上padding值，而不是margin值，因为最外层的LinearLayout的margin值我们其实是不用管的
            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                //noinspection unchecked
                ALL_VIEWS.add((ArrayList<View>) mLineViews.clone());
                //清空LINE_VIEWS后，把其放入新一行的第一个view
                mLineViews.clear();
                mLineViews.add(child);
                LINE_HEIGHT.add(lineHeight);

                //通过对比得到最大的宽度应该是多少，如果当前行的宽度更大的话，那整个空间的宽度width就是最大的这行
                width = Math.max(width, lineWidth);
                height += childHeight;
                //由于换行了，所以这里是重置下一行的lineWidth与lineHeight,并且值也都为第一个子控件的值
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                //这里是未换行的情况，要判定当前的子控件是否比同一行的其它空间高
                lineHeight = Math.max(lineHeight, childHeight);
                //当前行的宽度因为是同行，所以是累加就可以了
                lineWidth += childWidth;
                LatteLogger.d("wzt", "lineHeight = " + lineHeight);
                LatteLogger.d("wzt", "lineWidth = " + lineWidth);
                mLineViews.add(child);
            }
            //如果是最后一个子控件，则需要直接去计算高度与宽度
            if (i == childCount - 1) {
                height += lineHeight;
                width = Math.max(width, lineWidth);
                //noinspection unchecked
                ALL_VIEWS.add((ArrayList<View>) mLineViews.clone());
                LINE_HEIGHT.add(lineHeight);
                mLineViews.clear();
                LatteLogger.d("wzt", "height = " + height);
                LatteLogger.d("wzt", "width = " + width);
            }
        }

        //此处曾经忘记了加padding值，注意此处不要忘记加上padding值
        final int measureWidth = modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight();
        final int measureHeight = modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom();
        LatteLogger.d("wzt", "measureHeight = " + measureHeight + ";measureWidth = " + measureWidth);
        setMeasuredDimension(measureWidth, measureHeight);

        //计算出每个图片的大小
        final int imageSideLen = sizeWidth / mMaxLineNum;
        LatteLogger.d("wzt", imageSideLen);
        LatteLogger.d("wzt", "sizeWidth = " + sizeWidth);
        LatteLogger.d("wzt", "width = " + sizeWidth);
        if (!mIsOnceInitOnMeasure) {
            mParams = new LayoutParams(imageSideLen, imageSideLen);
            mIsOnceInitOnMeasure = true;
        }
    }

    /**
     * desc:此方法是在setContentView之后，onMeasure之前调用的，当View中所有的子控件均被映射成xml后触发
     * 可以在此方法进行一些初始化的操作
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initAddIcon();
    }

    private void initAddIcon() {
        mIconAdd = new IconTextView(getContext());
        mIconAdd.setText(ICON_TEXT);
        mIconAdd.setGravity(Gravity.CENTER);
        mIconAdd.setTextSize(mIconSize);
        mIconAdd.setBackgroundResource(R.drawable.border_text);
        mIconAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addView(mIconAdd);
    }
}
