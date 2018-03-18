package com.wzt.fastec.generators;

import wzt.latte_annotations.AppRegisterGenerator;
import wzt.latte_core.wechat.templates.AppRegisterTemplate;

/**
 * @author Tao
 * @date 2018/3/17
 * desc:
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packagename = "com.wzt.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
