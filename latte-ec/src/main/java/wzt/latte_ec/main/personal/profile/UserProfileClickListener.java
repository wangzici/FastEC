package wzt.latte_ec.main.personal.profile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import wzt.latte_core.delegates.LatteDelegate;
import wzt.latte_core.ui.camera.LatteCamera;
import wzt.latte_ec.R;
import wzt.latte_ec.main.personal.list.ListBean;

/**
 * @author Tao
 * @date 2018/3/28
 * desc:
 */
public class UserProfileClickListener extends SimpleClickListener {

    private final UserProfileDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(UserProfileDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        //根据ListBean里存储的id进行判断点击的是哪一项
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //开始照相机或选择图片
                LatteCamera.start(DELEGATE);
                break;
            case 2:
                final LatteDelegate nameDeleate = bean.getDelegate();
                DELEGATE.start(nameDeleate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                getDateDialog(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker picker, int year, int month, int dayOfMonth) {
                        final Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                        final String date = format.format(calendar.getTime());
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener onClickListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, onClickListener);
        builder.show();
    }

    private void getDateDialog(DatePickerDialog.OnDateSetListener dateSetListener) {
        final DatePickerDialog dialog = new DatePickerDialog(DELEGATE.getContext(), dateSetListener, 1994, 8, 25);
        dialog.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
