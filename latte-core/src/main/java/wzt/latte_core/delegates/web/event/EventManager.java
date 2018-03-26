package wzt.latte_core.delegates.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * @author Tao
 * @date 2018/3/26
 * desc:
 */
public class EventManager {
    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private static class HOLDER {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return HOLDER.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }
}
