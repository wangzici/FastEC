package wzt.latte_ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author Tao
 * @date 2018/3/25
 * desc:
 */
public class SectionContentItemEntity {

    private int mGoodsId = 0;
    private String mGoodsName = null;
    private String mGoodsThumb = null;

    public int getGoodsId() {
        return mGoodsId;
    }

    public void setGoodsId(int goodsId) {
        this.mGoodsId = goodsId;
    }

    public String getGoodsName() {
        return mGoodsName;
    }

    public void setGoodsName(String goodsName) {
        this.mGoodsName = goodsName;
    }

    public String getGoodsThumb() {
        return mGoodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.mGoodsThumb = goodsThumb;
    }
}
