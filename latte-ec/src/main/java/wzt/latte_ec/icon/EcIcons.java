package wzt.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @author Tao
 * @date 2018/2/25
 * desc:
 */
public enum EcIcons implements Icon{
    icon_share('\ue624'),
    icon_change('\ue653');

    char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
