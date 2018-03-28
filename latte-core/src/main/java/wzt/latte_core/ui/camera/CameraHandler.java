package wzt.latte_core.ui.camera;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import wzt.latte_core.R;
import wzt.latte_core.delegates.PermissionCheckerDelegate;

/**
 * @author Tao
 * @date 2018/3/28
 * desc:
 */
public class CameraHandler implements View.OnClickListener {
    private final PermissionCheckerDelegate DELEGATE;
    private final AlertDialog DIALOG;

    public CameraHandler(PermissionCheckerDelegate delegate) {
        DELEGATE = delegate;
        DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
    }

    final void beginCameraDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            //设置背景的暗度，0.0f是完全不暗，1.0f是背景全部变黑暗
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.photodialog_btn_cancel) {
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_take) {
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_native) {
            DIALOG.cancel();
        }
    }
}
