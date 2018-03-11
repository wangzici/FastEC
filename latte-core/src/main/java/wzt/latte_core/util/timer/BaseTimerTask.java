package wzt.latte_core.util.timer;

import java.util.TimerTask;

/**
 * @author Tao
 * @date 2018/3/11
 * desc:
 */
public class BaseTimerTask extends TimerTask{
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }


}
