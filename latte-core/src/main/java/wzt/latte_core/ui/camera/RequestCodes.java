package wzt.latte_core.ui.camera;

import com.yalantis.ucrop.UCrop;

/**
 * @author Tao
 * @date 2018/3/28
 * desc:
 */
public class RequestCodes {
    public static final int TAKE_PHOTO = 4;
    public static final int PICK_PHOTO = 5;
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;
    public static final int SCAN = 7;
}
