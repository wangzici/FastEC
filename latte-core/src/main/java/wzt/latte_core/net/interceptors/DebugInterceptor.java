package wzt.latte_core.net.interceptors;

import android.support.annotation.RawRes;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import wzt.latte_core.util.FileUtil;

/**
 * @author Tao
 * @date 2018/3/11
 * desc:
 */
public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        DEBUG_URL = debugUrl;
        DEBUG_RAW_ID = rawId;
    }

    private Response getResponse(Chain chain , String json) {
        return new Response.Builder()
                .code(200)
                .message("OK")
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response getResponse(Chain chain , @RawRes int rawId) {
        String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().toString();
        if (url.contains(DEBUG_URL)) {
            return getResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(request);
    }
}
