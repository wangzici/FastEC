package wzt.latte_core.net;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wzt.latte_core.app.ConfigType;
import wzt.latte_core.app.Latte;

/**
 * @author Tao
 * @date 2018/3/6
 * desc:
 */
public class RestCreator {
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        @SuppressWarnings("unchecked")
        private static OkHttpClient.Builder addInterceptors() {
            ArrayList<Interceptor> interceptors = (ArrayList<Interceptor>) Latte.getConfigurations().get(ConfigType.INTERCEPTOR.name());
            if (interceptors != null && !interceptors.isEmpty()) {
                for (Interceptor interceptor : interceptors) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptors()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
