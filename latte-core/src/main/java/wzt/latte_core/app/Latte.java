package wzt.latte_core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 *
 * @author Tao
 * @date 2018/2/24
 * desc:
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context);
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getLatteConfigs();
    }

    public static Object getConfiguration(String key) {
        return Configurator.getLatteConfigs().get(key);
    }

    public static Context getApplicationContext() {
        return (Context) getConfiguration(ConfigType.APPLICATION_CONTEXT.name());
    }

    public static Handler getHandler() {
        return (Handler) getConfiguration(ConfigType.HANDLER.name());
    }

}
