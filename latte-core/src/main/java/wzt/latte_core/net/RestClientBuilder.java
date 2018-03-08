package wzt.latte_core.net;

import android.content.Context;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import wzt.latte_core.net.callback.IError;
import wzt.latte_core.net.callback.IFailure;
import wzt.latte_core.net.callback.IRequest;
import wzt.latte_core.net.callback.ISuccess;
import wzt.latte_core.ui.LoaderStyle;

/**
 * @author Tao
 * @date 2018/3/6
 * desc:
 */
public class RestClientBuilder {
    private String mUrl;
    private WeakHashMap<String, Object> mParams = new WeakHashMap<>();
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private IRequest mRequest;
    private RequestBody mRequestBody;
    private Context mContext;
    private LoaderStyle mLoadingType;

    public RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public RestClientBuilder params(String key,Object value) {
        mParams.put(key, value);
        return this;
    }

    public RestClientBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    public RestClientBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public RestClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    public RestClientBuilder request(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public RestClientBuilder requestBody(RequestBody requestBody) {
        this.mRequestBody = requestBody;
        return this;
    }

    public RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoadingType = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public RestClientBuilder loader(Context context,LoaderStyle loadingType) {
        this.mContext = context;
        this.mLoadingType = loadingType;
        return this;
    }

    public RestClient build() {
        return new RestClient(mUrl, mParams, mSuccess, mFailure, mError, mRequest, mRequestBody, mContext, mLoadingType);
    }
}
