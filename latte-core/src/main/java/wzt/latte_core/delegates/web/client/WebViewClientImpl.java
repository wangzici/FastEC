package wzt.latte_core.delegates.web.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import wzt.latte_core.delegates.web.WebDelegate;
import wzt.latte_core.delegates.web.route.Router;
import wzt.latte_core.util.log.LatteLogger;

/**
 * @author Tao
 * @date 2018/3/25
 * desc:
 */
public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

}
