package com.wzt.fastec.example.event;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.sharesdk.onekeyshare.OnekeyShare;
import wzt.latte_core.delegates.web.event.Event;
import wzt.latte_core.util.log.LatteLogger;

/**
 * @author Tao
 * @date 2018/3/31
 * desc:
 */
public class ShareEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if ("share".equals(getAction())) {
            LatteLogger.json("ShareEvent", params);
            showShare(params);
        }
        return null;
    }

    private void showShare(String params) {

        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");


        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(title);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(text);
        // imageUrl是image的网页路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(imageUrl);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("我是Site");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("我是siteUrl");

        // 启动分享GUI
        oks.show(getContext());
    }
}
