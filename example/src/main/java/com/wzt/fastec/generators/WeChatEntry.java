package com.wzt.fastec.generators;

import wzt.latte_annotations.EntryGenerator;
import wzt.latte_core.wechat.templates.WXEntryTemplate;

/**
 * @author Tao
 * @date 2018/3/17
 * desc:
 */
@SuppressWarnings("unused")
@EntryGenerator(
        packagename = "com.wzt.fastec.example",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
