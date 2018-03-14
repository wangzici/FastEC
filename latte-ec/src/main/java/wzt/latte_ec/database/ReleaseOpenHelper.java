package wzt.latte_ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Tao
 * @date 2018/3/14
 * desc:
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper{
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}
