package wzt.latte_core.ui.recyclerview;

import java.util.LinkedHashMap;

/**
 * @author Tao
 * @date 2018/3/22
 * desc:
 */
public class MultipleEntityBuilder {
    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
        FIELDS.clear();
    }

    public final MultipleEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleEntityBuilder setField(LinkedHashMap<Object, Object> map) {
        FIELDS.putAll(map);
        return this;
    }


    public final MultipleItemEntity build() {
        return  new MultipleItemEntity(FIELDS);
    }
}
