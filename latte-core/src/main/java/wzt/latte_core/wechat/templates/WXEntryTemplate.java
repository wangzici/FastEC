package wzt.latte_core.wechat.templates;

import wzt.latte_core.activities.ProxyActivity;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.wechat.BaseWXEntryActivity;
import wzt.latte_core.wechat.LatteWeChat;

/**
 * @author Tao
 * @date 2018/3/17
 * desc:
 */
public class WXEntryTemplate extends BaseWXEntryActivity{

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
