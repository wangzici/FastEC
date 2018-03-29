package wzt.latte_ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;

import butterknife.BindView;
import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.net.RestClient;
import wzt.latte_core.net.callback.ISuccess;
import wzt.latte_ec.R;
import wzt.latte_ec.R2;

/**
 * @author Tao
 * @date 2018/3/29
 * desc:
 */
public class AboutDelegate extends LatteDelegate{
    @BindView(R2.id.tv_info)
    AppCompatTextView mTextView = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .url("about.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTextView.setText(info);
                    }
                })
                .build()
                .get();
    }
}
