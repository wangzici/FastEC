package wzt.latte_core.ui.camera;

import android.net.Uri;

import wzt.latte_core.delegates.PermissionCheckerDelegate;
import wzt.latte_core.util.file.FileUtil;

/**
 * @author Tao
 * @date 2018/3/28
 * desc:
 */
public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
