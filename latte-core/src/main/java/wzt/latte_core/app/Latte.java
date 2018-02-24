package wzt.latte_core.app;

import android.content.Context;

import java.util.WeakHashMap;

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

    private static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getLatteConfigs();
    }

}
