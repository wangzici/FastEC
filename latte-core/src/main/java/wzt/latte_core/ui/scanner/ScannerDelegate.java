package wzt.latte_core.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.util.callback.CallBackManager;
import wzt.latte_core.util.callback.CallbackType;
import wzt.latte_core.util.callback.IGlobalCallback;

/**
 * @author Tao
 * @date 2018/3/31
 * desc:
 */
public class ScannerDelegate extends LatteDelegate implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mZbBarScannerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mZbBarScannerView == null) {
            mZbBarScannerView = new ZBarScannerView(getContext());
            mZbBarScannerView.setAutoFocus(true);
        }
    }

    @Override
    public Object setLayout() {
        return mZbBarScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mZbBarScannerView != null) {
            mZbBarScannerView.setResultHandler(this);
            mZbBarScannerView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mZbBarScannerView != null) {
            mZbBarScannerView.stopCamera();
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void handleResult(Result result) {
        @SuppressWarnings("unchecked")
        IGlobalCallback<String> callback = CallBackManager.getInstance().getCallback(CallbackType.ON_SCAN);
        if (callback != null && result != null) {
            callback.executeCallback(result.getContents());
        }
        this.pop();
    }
}
