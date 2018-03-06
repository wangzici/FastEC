package com.wzt.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.net.RestClient;
import wzt.latte_core.net.callback.IError;
import wzt.latte_core.net.callback.IFailure;
import wzt.latte_core.net.callback.IRequest;
import wzt.latte_core.net.callback.ISuccess;

/**
 * @author Tao
 * @date 2018/2/26
 * desc:
 */
public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://www.baidu.com")
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {
                    }

                    @Override
                    public void onRequestEnd() {
                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "[onError] code = " + code, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "[onFailure]", Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();

    }
}
