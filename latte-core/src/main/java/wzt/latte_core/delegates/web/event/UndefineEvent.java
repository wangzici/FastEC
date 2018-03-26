package wzt.latte_core.delegates.web.event;

import wzt.latte_core.util.log.LatteLogger;

/**
 * @author Tao
 * @date 2018/3/26
 * desc:
 */
public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
