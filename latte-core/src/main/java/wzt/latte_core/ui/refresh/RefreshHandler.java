package wzt.latte_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import wzt.latte_core.app.Latte;
import wzt.latte_core.net.RestClient;
import wzt.latte_core.net.callback.ISuccess;

/**
 * @author Tao
 * @date 2018/3/20
 * desc:
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener{
    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        firstPage("index.php");
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                })
                .build()
                .get();
    }
}
