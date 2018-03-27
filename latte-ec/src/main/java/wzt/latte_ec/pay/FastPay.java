package wzt.latte_ec.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_ec.R;

/**
 * @author Tao
 * @date 2018/3/27
 * desc:
 */
public class FastPay implements View.OnClickListener {
    //设置支付回调监听
    private IAlPayResultListener mIAlPayResultListener = null;
    private Activity mActivity = null;

    private AlertDialog mDialog = null;

    public FastPay(LatteDelegate delegate) {
        mActivity = delegate.getProxyActivity();
        mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public void beginPayDialog() {
        mDialog.show();
        Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            //不设置背景颜色，则会出现宽度不会显示全的现象
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams params = window.getAttributes();
            //此处如果不加MATCH_PARENT，也会出现宽度显示不全的现象
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            //这个的内容是让该window后所有的东西都成暗淡，不过此处其实不加也是一样的效果
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.btn_dialog_pay_alpay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }

    }

    public FastPay setPayResultListener(IAlPayResultListener listener) {
        this.mIAlPayResultListener = listener;
        return this;
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.btn_dialog_pay_alpay) {
            mDialog.dismiss();
        } else if (id == R.id.btn_dialog_pay_wechat) {
            mDialog.dismiss();
        } else if (id == R.id.btn_dialog_pay_cancel) {
            mDialog.dismiss();
        }
    }
}
