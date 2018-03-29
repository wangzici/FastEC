package wzt.latte_core.delegates;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.yalantis.ucrop.UCrop;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;
import wzt.latte_core.ui.camera.CameraImageBean;
import wzt.latte_core.ui.camera.LatteCamera;
import wzt.latte_core.ui.camera.RequestCodes;
import wzt.latte_core.util.callback.CallBackManager;
import wzt.latte_core.util.callback.CallbackType;
import wzt.latte_core.util.callback.IGlobalCallback;
import wzt.latte_core.util.log.LatteLogger;

/**
 * @author Tao
 * @date 2018/2/26
 * desc:
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {
    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    void startCamera() {
        LatteCamera.start(this);
    }

    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }

    @OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    void onCameraDenied() {
        Toast.makeText(getContext(), "读写权限被拒绝", Toast.LENGTH_LONG).show();
    }

    /**
     * desc:当用户对请求权限的dialog做出响应后，会调用onRequestPermissionsResult,此处相当于把该方法调用到PermissionCheckerDelegate的对应注解方法
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RequestCodes.TAKE_PHOTO:
                if (data != null) {
                    final Uri uri = CameraImageBean.getInstance().getPath();
                        UCrop.of(uri, uri)
                                .withAspectRatio(400, 400)
                                .start(getContext(), this);
                }
                break;
            case RequestCodes.PICK_PHOTO:
                if (data != null) {
                    final Uri pickPath = data.getData();
                    //从相册选择后需要有个路径存放剪裁过的图片
                    if (pickPath != null) {
                        final String pickCropPath = LatteCamera.createCropFile().getPath();
                        UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withAspectRatio(400, 400)
                                .start(getContext(), this);
                    }
                }
                break;
            case RequestCodes.CROP_PHOTO:
                final Uri cropUri = UCrop.getOutput(data);
                //拿到剪裁后的数据进行处理
                @SuppressWarnings("unchecked")
                IGlobalCallback<Uri> callback = CallBackManager.getInstance().getCallback(CallbackType.ON_CROP);
                if (callback != null) {
                    callback.executeCallback(cropUri);
                }
                break;
            case RequestCodes.CROP_ERROR:
                Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
