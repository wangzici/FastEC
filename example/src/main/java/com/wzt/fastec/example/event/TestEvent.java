package com.wzt.fastec.example.event;

import android.webkit.WebView;
import android.widget.Toast;

import wzt.latte_core.delegates.web.event.Event;

/**
 * @author Tao
 * @date 2018/3/26
 * desc:
 */
public class TestEvent extends Event{
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if ("test".equals(getAction())) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
