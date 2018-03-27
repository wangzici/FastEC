package wzt.latte_ec.pay;

/**
 * @author Tao
 * @date 2018/3/27
 * desc:
 */
public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
