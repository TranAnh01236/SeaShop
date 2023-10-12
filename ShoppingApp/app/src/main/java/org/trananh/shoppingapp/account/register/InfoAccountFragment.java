package org.trananh.shoppingapp.account.register;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.model.User;

import java.sql.Date;
import java.util.Calendar;
import java.util.Locale;

public class InfoAccountFragment extends Fragment {
    public static final String TAG = InfoAccountFragment.class.getName();
    private RegisterActivity mRegisterActivity;
    private View rootView;
    private User user;
    private EditText txtFirstName, txtLastName, txtId, txtPhone, txtDate;
    private RadioButton radMale, radFemale;
    private LinearLayout linearLayoutConfirm;
    private DatePickerDialog datePickerDialog;
    private TextView tvError;
    private InfoAccountFragmentListener mInfoAccountFragmentListener;
    public interface InfoAccountFragmentListener{
        void confirmClick(User user);
    }
    public InfoAccountFragment(InfoAccountFragmentListener listener) {
        this.mInfoAccountFragmentListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRegisterActivity = (RegisterActivity)getActivity();
        rootView = inflater.inflate(R.layout.fragment_info_account, container, false);
        initialize();
        return rootView;
    }
    private void initialize(){
        Bundle bundle = getArguments();
        if (bundle != null){
            user = (User) bundle.getSerializable("object_user");
            if (user != null){
                txtFirstName = rootView.findViewById(R.id.edit_text_first_name);
                txtLastName = rootView.findViewById(R.id.edit_text_last_name);
                txtId = rootView.findViewById(R.id.edit_text_id);
                txtPhone = rootView.findViewById(R.id.edit_text_phone);
                txtDate = rootView.findViewById(R.id.edit_text_date);
                linearLayoutConfirm = rootView.findViewById(R.id.linear_layout_accept);
                tvError = rootView.findViewById(R.id.text_view_error);

                txtPhone.setFocusable(false);
                txtDate.setFocusable(false);

                txtPhone.setText(user.getPhoneNumber());

                radMale = rootView.findViewById(R.id.radio_button_male);
                radFemale = rootView.findViewById(R.id.radio_button_female);

                linearLayoutConfirm.setClickable(false);

                initDatePicker();

                txtFirstName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enableConfirm();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });
                txtLastName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enableConfirm();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });
                txtId.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enableConfirm();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });
                txtDate.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enableConfirm();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                txtDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openDatePicker();
                    }
                });

                linearLayoutConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirm();
                    }
                });

            }
        }
    }

    private void enableConfirm(){
        tvError.setText("");
        String fName = txtFirstName.getText().toString().trim();
        String lName = txtLastName.getText().toString().trim();
        String id = txtId.getText().toString().trim();
        String date = txtDate.getText().toString().trim();

        if (!fName.equals("") && !lName.equals("") && !id.equals("") && !date.equals("")){
            linearLayoutConfirm.setClickable(true);
            linearLayoutConfirm.setBackgroundResource(R.drawable.custom_btn_addcart_hightlight);
        }else{
            linearLayoutConfirm.setClickable(false);
            linearLayoutConfirm.setBackgroundResource(R.drawable.custom_btn_addcart);
        }

    }

    private void confirm(){
        tvError.setText("");
        String fName = txtFirstName.getText().toString().trim();
        String lName = txtLastName.getText().toString().trim();
        String id = txtId.getText().toString().trim();
        String date = txtDate.getText().toString().trim();

        int gender = 0;
        if (radFemale.isChecked()){
            gender = 1;
        }

        if (id.length() < 9 || id.length() > 12){
            tvError.setText("Mã CCCD/CMND không hợp lệ");
            return;
        }

        String d[] = date.split("/");
        int year, month, day;
        try {
            year = Integer.parseInt(d[2]);
            month = Integer.parseInt(d[1]);
            day = Integer.parseInt(d[0]);
        }catch (Exception e){
            tvError.setText("Ngày sinh không hợp lệ");
            return;
        }

        if ((Calendar.getInstance().get(Calendar.YEAR) - year) < 16){
            tvError.setText("Bé hơn 16 tuổi không thể đăng ký thành viên");
            return;
        }
        Date date1 = Date.valueOf(year + "-" + month + "-" + day);

        user.setFirstName(fName);
        user.setLastName(lName);
        user.setId(id);
        user.setDayOfBirth(date1);
        user.setGender(gender);

        mInfoAccountFragmentListener.confirmClick(user);

    }

    private void initDatePicker(){

        Locale.setDefault(Locale.CHINESE);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                showDateOnView(day, month, year);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(mRegisterActivity, style, dateSetListener, year, month, day);
    }

    private void showDateOnView(int day, int month, int year){
        txtDate.setText( day + "/" + month + "/" + year );
    }

    private void openDatePicker(){
        datePickerDialog.show();
    }

}