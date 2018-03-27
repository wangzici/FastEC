package wzt.latte_core.wechat.templates;

import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;

import wzt.latte_core.activities.ProxyActivity;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.wechat.BaseWXEntryActivity;
import wzt.latte_core.wechat.BaseWXPayEntryActivity;

/**
 * @author Tao
 * @date 2018/3/17
 * desc:
 */
public class WXPayEntryTemplate extends BaseWXPayEntryActivity {

    @Override
    protected void onPaySuccess() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
