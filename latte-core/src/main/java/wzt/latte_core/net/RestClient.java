package wzt.latte_core.net;

import android.content.Context;
import android.util.Log;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import wzt.latte_core.net.callback.IError;
import wzt.latte_core.net.callback.IFailure;
import wzt.latte_core.net.callback.IRequest;
import wzt.latte_core.net.callback.ISuccess;
import wzt.latte_core.net.callback.RequestCallBacks;
import wzt.latte_core.ui.LatteLoader;
import wzt.latte_core.ui.LoaderStyle;

/**
 * @author Tao
 * @date 2018/3/6
 * desc:
 */
public class RestClient {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final RequestBody REQUESTBODY;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;


    RestClient(String url, WeakHashMap<String, Object> params, ISuccess success, IFailure failure, IError error, IRequest request, RequestBody requestBody,Context context,LoaderStyle loadingType) {
        this.URL = url;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
        this.REQUESTBODY = requestBody;
        this.CONTEXT = context;
        this.LOADER_STYLE = loadingType;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod httpMethod) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT);
        }

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (httpMethod) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.put(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallBacks());
        }
    }

    private RequestCallBacks getRequestCallBacks() {
        return new RequestCallBacks(REQUEST, SUCCESS, ERROR, FAILURE, LOADER_STYLE);
    }


    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}
