package wzt.latte_ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import wzt.latte_core.delegates.bottom.BaseBottomDelegate;
import wzt.latte_core.delegates.bottom.BottomItemDelegate;
import wzt.latte_core.delegates.bottom.BottomTabBean;
import wzt.latte_core.delegates.bottom.ItemBuilder;
import wzt.latte_ec.main.cart.ShopCartDelegate;
import wzt.latte_ec.main.discover.DiscoverDelegate;
import wzt.latte_ec.main.index.IndexDelegate;
import wzt.latte_ec.main.sort.SortDelegate;

/**
 * @author Tao
 * @date 2018/3/19
 * desc:
 */
public class ECBottomDelegate extends BaseBottomDelegate{
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
