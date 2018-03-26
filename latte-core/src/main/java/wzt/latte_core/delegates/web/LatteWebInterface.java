package wzt.latte_core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import wzt.latte_core.delegates.web.event.Event;
import wzt.latte_core.delegates.web.event.EventManager;

/**
 * @author Tao
 * @date 2018/3/25
 * desc:
 */
public class LatteWebInterface {
    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event
                    .setAction(action)
                    .setDelegate(DELEGATE)
                    .setContext(DELEGATE.getContext())
                    .setUrl(DELEGATE.getUrl())
                    .setWebView(DELEGATE.getWebView());
            event.execute(params);
        }
        return null;
    }
}
