package wzt.latte_core.delegates;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;
import wzt.latte_core.ui.camera.LatteCamera;

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
    }
}
