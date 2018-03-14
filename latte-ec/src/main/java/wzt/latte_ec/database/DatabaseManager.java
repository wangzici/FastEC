package wzt.latte_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author Tao
 * @date 2018/3/14
 * desc:
 */
public class DatabaseManager {

    private UserProfileDao mDao;

    private DatabaseManager() {

    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public UserProfileDao getDao() {
        return mDao;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();
        mDao = daoSession.getUserProfileDao();
    }

}
