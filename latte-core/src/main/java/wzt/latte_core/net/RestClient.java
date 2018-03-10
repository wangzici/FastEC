package wzt.latte_core.net;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import wzt.latte_core.net.callback.IError;
import wzt.latte_core.net.callback.IFailure;
import wzt.latte_core.net.callback.IRequest;
import wzt.latte_core.net.callback.ISuccess;
import wzt.latte_core.net.callback.RequestCallBacks;
import wzt.latte_core.net.download.DownloadHandler;
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
    private final RequestBody BODY;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENTION;
    private final String NAME;


    RestClient(String url,
               WeakHashMap<String, Object> params,
               ISuccess success,
               IFailure failure,
               IError error,
               IRequest request,
               RequestBody requestBody,
               Context context,
               LoaderStyle loadingType,
               File file,
               String donwloadDir,
               String extension,
               String name) {
        this.URL = url;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
        this.BODY = requestBody;
        this.CONTEXT = context;
        this.LOADER_STYLE = loadingType;
        this.FILE = file;
        this.DOWNLOAD_DIR = donwloadDir;
        this.EXTENTION = extension;
        this.NAME = name;
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
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case UPLOAD:
                RequestBody requestBody = RequestBody.create(MultipartBody.FORM, FILE);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
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
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void download() {
        new DownloadHandler(URL, PARAMS, REQUEST, DOWNLOAD_DIR, EXTENTION, NAME, SUCCESS, FAILURE, ERROR).handleDownload();
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}
