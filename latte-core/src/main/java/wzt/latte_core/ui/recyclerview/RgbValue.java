package wzt.latte_core.ui.recyclerview;

import com.google.auto.value.AutoValue;

/**
 * @author Tao
 * @date 2018/3/23
 * desc:
 */
@AutoValue
public abstract class RgbValue {
    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
