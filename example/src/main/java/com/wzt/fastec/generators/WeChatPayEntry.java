package com.wzt.fastec.generators;

import wzt.latte_annotations.PayEntryGenerator;
import wzt.latte_core.wechat.templates.WXPayEntryTemplate;

/**
 * @author Tao
 * @date 2018/3/17
 * desc:
 */
@PayEntryGenerator(
        packagename = "com.wzt.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
