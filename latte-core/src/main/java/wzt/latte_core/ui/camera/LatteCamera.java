package wzt.latte_core.ui.camera;

import wzt.latte_core.delegates.PermissionCheckerDelegate;

/**
 * @author Tao
 * @date 2018/3/28
 * desc:
 */
public class LatteCamera {
    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
