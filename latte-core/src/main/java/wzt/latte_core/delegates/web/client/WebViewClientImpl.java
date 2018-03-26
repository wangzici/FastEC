package wzt.latte_core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wzt.latte_core.app.Latte;
import wzt.latte_core.delegates.web.IPageLoadListener;
import wzt.latte_core.delegates.web.WebDelegate;
import wzt.latte_core.delegates.web.route.Router;
import wzt.latte_core.ui.LatteLoader;
import wzt.latte_core.util.log.LatteLogger;

/**
 * @author Tao
 * @date 2018/3/25
 * desc:
 */
public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener;
    private final Handler HANDLER = Latte.getHandler();

    public WebViewClientImpl(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    public void setPageLoadListener(IPageLoadListener listener) {
        mIPageLoadListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(DELEGATE.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }
}
