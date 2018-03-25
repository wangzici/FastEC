package wzt.latte_ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import wzt.latte_core.ui.recyclerview.DataConverter;
import wzt.latte_core.ui.recyclerview.ItemType;
import wzt.latte_core.ui.recyclerview.MultipleFields;
import wzt.latte_core.ui.recyclerview.MultipleItemEntity;

/**
 * @author Tao
 * @date 2018/3/24
 * desc:
 */
public class VerticalListConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray jsonArray = JSON.parseObject(getJsonData()).getJSONObject("data").getJSONArray("list");
        final int arraySize = jsonArray.size();
        for (int i = 0; i < arraySize; i++) {
            final JSONObject data = jsonArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, name)
                    .setField(MultipleFields.TAG, false)
                    .build();
            ENTITIES.add(entity);
        }
        ENTITIES.get(0).setField(MultipleFields.TAG, true);
        return ENTITIES;
    }

}
