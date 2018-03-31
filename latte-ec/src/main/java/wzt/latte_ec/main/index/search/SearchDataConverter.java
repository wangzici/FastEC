package wzt.latte_ec.main.index.search;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;

import wzt.latte_core.ui.recyclerview.DataConverter;
import wzt.latte_core.ui.recyclerview.MultipleFields;
import wzt.latte_core.ui.recyclerview.MultipleItemEntity;
import wzt.latte_core.util.storage.LattePreference;

/**
 * @author Tao
 * @date 2018/3/31
 * desc:
 */
public class SearchDataConverter extends DataConverter {
    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        ArrayList<MultipleItemEntity> arrayList = new ArrayList<>();
        //这里的数据是从SharedPreference里直接取的
        final String jsonStr =
                LattePreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!TextUtils.isEmpty(jsonStr)) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                arrayList.add(entity);
            }
        }
        return arrayList;
    }

}
