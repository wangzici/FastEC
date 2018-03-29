package wzt.latte_core.util.callback;


import android.support.annotation.NonNull;

import java.util.WeakHashMap;

/**
 * @author Tao
 * @date 2018/3/29
 * desc:
 */
public class CallBackManager {
    private final WeakHashMap<CallbackType, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private CallBackManager() {

    }

    public static CallBackManager getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final CallBackManager INSTANCE = new CallBackManager();
    }

    public CallBackManager addCallback(@NonNull CallbackType type, @NonNull IGlobalCallback callback) {
        CALLBACKS.put(type, callback);
        return this;
    }

    public IGlobalCallback getCallback(CallbackType type) {
        return CALLBACKS.get(type);
    }
}
