package org.trananh.shoppingapp.account.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.controller.UserController;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.MyHttpResponse;

public class LoginNamePasswordFragment extends Fragment {
    public static final String TAG = LoginNamePasswordFragment.class.getName();
    private RegisterActivity mRegisterActivity;
    private View rootView;
    private String phoneNumber;
    private EditText txtAccountName, txtPassword, txtConfirmPassword;
    private TextView tvError1, tvError2;
    private LinearLayout linearLayoutConfirm;
    private UserController mUserController;
    private LoginNamePasswordFragmentListener mLoginNamePasswordFragmentListener;
    public interface LoginNamePasswordFragmentListener{
        void confirmClick(User user);
    }
    public LoginNamePasswordFragment(LoginNamePasswordFragmentListener listener) {
        this.mLoginNamePasswordFragmentListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRegisterActivity = (RegisterActivity)getActivity();
        rootView = inflater.inflate(R.layout.fragment_login_name_password, container, false);
        initialize();
        return rootView;
    }

    private void initialize(){
        Bundle bundle = getArguments();
        if (bundle != null){
            String phoneNumber1 = (String) bundle.get("string_phone_number");
            if (phoneNumber1 != null){
                phoneNumber = "0" + phoneNumber1.substring(3);
                mUserController = new UserController();
                txtAccountName = rootView.findViewById(R.id.edit_text_login_name);
                txtPassword = rootView.findViewById(R.id.edit_text_password);
                txtConfirmPassword = rootView.findViewById(R.id.edit_text_confirm_password);
                tvError1 = rootView.findViewById(R.id.text_view_error_account);
                tvError2 = rootView.findViewById(R.id.text_view_error_password);
                linearLayoutConfirm = rootView.findViewById(R.id.linear_layout_phone);

                txtAccountName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enableButton();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });
                txtPassword.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enableButton();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });
                txtConfirmPassword.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enableButton();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                linearLayoutConfirm.setClickable(false);

                linearLayoutConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirm();
                    }
                });

            }
        }
    }

    private void enableButton(){
        tvError1.setText("");
        tvError2.setText("");
        String loginName = txtAccountName.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String confirmPassword = txtConfirmPassword.getText().toString().trim();
        if (!loginName.equals("") && !password.equals("") && !confirmPassword.equals("")){
            linearLayoutConfirm.setClickable(true);
            linearLayoutConfirm.setBackgroundResource(R.drawable.custom_btn_addcart_hightlight);
        }else{
            linearLayoutConfirm.setClickable(false);
            linearLayoutConfirm.setBackgroundResource(R.drawable.custom_btn_addcart);
        }
    }

    private void confirm(){

        Log.e(TAG,"Click");

        tvError1.setText("");
        tvError2.setText("");

        String loginName = txtAccountName.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String confirmPassword = txtConfirmPassword.getText().toString().trim();

        if (!loginName.matches("[A-Za-z0-9]{6,20}")){
            tvError1.setText("Tên tài khoản không hợp lệ");
            return;
        }

        if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$")){
            tvError2.setText("Mật khẩu không hợp lệ");
            return;
        }

        Log.e(TAG, loginName + "-" + password + "-" + confirmPassword);

        if (!password.equals(confirmPassword)){
            tvError2.setText("Mật khẩu không khớp");
            return;
        }

        MyHttpResponse rs = mUserController.getByLoginName(loginName);
        if (rs.getStatusCode() == 200){
            tvError1.setText("Tên tài khoản đã tồn tại");
            return;
        }

        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);

        mLoginNamePasswordFragmentListener.confirmClick(user);

    }

}