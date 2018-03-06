package wzt.latte_core.net;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import wzt.latte_core.net.callback.IError;
import wzt.latte_core.net.callback.IFailure;
import wzt.latte_core.net.callback.IRequest;
import wzt.latte_core.net.callback.ISuccess;
import wzt.latte_core.net.callback.RequestCallBacks;

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

    RestClient(String url, WeakHashMap<String, Object> params, ISuccess success, IFailure failure, IError error, IRequest request, RequestBody requestBody) {
        this.URL = url;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
        this.REQUESTBODY = requestBody;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod httpMethod) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

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
        return new RequestCallBacks(REQUEST, SUCCESS, ERROR, FAILURE);
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
