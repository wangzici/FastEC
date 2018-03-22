package wzt.latte_core.ui.recyclerview;

import java.util.ArrayList;

/**
 * @author Tao
 * @date 2018/3/22
 * desc:
 */
public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        mJsonData = json;
        return this;
    }

    public String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }

}
