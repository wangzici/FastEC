package wzt.latte_core.ui.recyclerview;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * @author Tao
 * @date 2018/3/22
 * desc:
 */
public class MultipleItemEntity implements MultiItemEntity {
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELd = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FILEDS_REFERENCE = new SoftReference<>(MULTIPLE_FIELd, ITEM_QUEUE);

    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        setField(fields);
    }

    public static MultipleEntityBuilder builder() {
        return new MultipleEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FILEDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FILEDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<Object, Object> getFields() {
        return FILEDS_REFERENCE.get();
    }

    public final MultiItemEntity setField(Object key, Object value) {
        FILEDS_REFERENCE.get().put(key, value);
        return this;
    }

    public final MultiItemEntity setField(LinkedHashMap<Object, Object> hashMap) {
        FILEDS_REFERENCE.get().putAll(hashMap);
        return this;
    }
}
