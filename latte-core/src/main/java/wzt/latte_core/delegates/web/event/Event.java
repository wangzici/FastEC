package wzt.latte_core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import wzt.latte_core.delegates.web.WebDelegate;

/**
 * @author Tao
 * @date 2018/3/26
 * desc:
 */
public abstract class Event implements IEvent {
    private Context mContext = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Context getContext() {
        return mContext;
    }

    public Event setContext(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    public String getAction() {
        return mAction;
    }

    public Event setAction(String mAction) {
        this.mAction = mAction;
        return this;
    }

    public WebDelegate getDelegate() {
        return mDelegate;
    }

    public Event setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    public Event setUrl(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public WebView getWebView() {
        return mWebView;
    }

    public Event setWebView(WebView mWebView) {
        this.mWebView = mWebView;
        return this;
    }
}
