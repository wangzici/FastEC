package wzt.latte_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Tao
 * @date 2018/3/17
 * desc:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface AppRegisterGenerator {
    String packagename();

    Class<?> registerTemplate();
}
