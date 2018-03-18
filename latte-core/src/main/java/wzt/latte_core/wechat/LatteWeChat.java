package wzt.latte_core.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import wzt.latte_core.app.ConfigType;
import wzt.latte_core.app.Latte;
import wzt.latte_core.wechat.callbacks.IWeChatSignInCallback;

/**
 * @author Tao
 * @date 2018/3/18
 * desc:
 */
public class LatteWeChat {
    public static final String APP_ID = (String) Latte.getConfigurations().get(ConfigType.WE_CHAT_APP_ID.name());
    public static final String APP_SECRET = (String) Latte.getConfigurations().get(ConfigType.WE_CHAT_APP_SECRET.name());
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private LatteWeChat() {
        final Activity activity = (Activity) Latte.getConfiguration(ConfigType.ACTIVITY.name());
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    private static final class Holder{
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public IWXAPI getWXAPI() {
        return WXAPI;
    }

    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
