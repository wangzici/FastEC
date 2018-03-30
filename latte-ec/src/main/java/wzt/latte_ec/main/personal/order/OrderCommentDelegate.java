package wzt.latte_ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.ui.widget.AutoPhotoLayout;
import wzt.latte_core.ui.widget.StarLayout;
import wzt.latte_core.util.callback.CallBackManager;
import wzt.latte_core.util.callback.CallbackType;
import wzt.latte_core.util.callback.IGlobalCallback;
import wzt.latte_ec.R;
import wzt.latte_ec.R2;

/**
 * @author Tao
 * @date 2018/3/30
 * desc:
 */
public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout;

    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        Toast.makeText(getContext(), "评分： " + mStarLayout.getStarCount(), Toast.LENGTH_LONG).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallBackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
            @Override
            public void executeCallback(@Nullable Uri args) {
                mAutoPhotoLayout.onCropTarget(args);
            }
        });
    }
}
