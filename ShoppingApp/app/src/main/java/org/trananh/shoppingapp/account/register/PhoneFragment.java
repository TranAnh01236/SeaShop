package org.trananh.shoppingapp.account.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.account.login.LoginActivity;
import org.trananh.shoppingapp.controller.UserController;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.Constants;
import org.trananh.shoppingapp.util.MyHttpResponse;

public class PhoneFragment extends Fragment {

    private RegisterActivity mRegisterActivity;
    private View rootView;
    private PhoneFragmentListener mPhoneFragmentListener;
    private ImageButton btnBack;
    private EditText txtPhone;
    private TextView tvError;
    private String phoneNumber = "";
    private LinearLayout linearLayoutPhone;
    private UserController mUserController;
    public interface PhoneFragmentListener{
        void verifyClick(String phoneNumber);
    }
    public PhoneFragment(PhoneFragmentListener listener) {
        this.mPhoneFragmentListener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRegisterActivity = (RegisterActivity)getActivity();
        rootView = inflater.inflate(R.layout.fragment_phone, container, false);
        initialize();
        return rootView;
    }

    private void initialize(){
        mUserController = new UserController();
        txtPhone = rootView.findViewById(R.id.edit_text_phone);
        linearLayoutPhone = rootView.findViewById(R.id.linear_layout_phone);
        tvError = rootView.findViewById(R.id.text_view_error);

        btnBack = rootView.findViewById(R.id.image_button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterActivity.onBackPressed();
            }
        });

        txtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                phoneNumber = txtPhone.getText().toString().trim();
                if (phoneNumber.length() == 10){
                    linearLayoutPhone.setBackgroundResource(R.drawable.custom_btn_addcart_hightlight);
                    linearLayoutPhone.setClickable(true);
                }else{
                    linearLayoutPhone.setBackgroundResource(R.drawable.custom_btn_addcart);
                    linearLayoutPhone.setClickable(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        linearLayoutPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyHttpResponse rs = mUserController.getUserByPhoneNumber(phoneNumber);
                if (rs != null) {
                    if (rs.getStatusCode() == 200){
                        tvError.setText("Số điện thoại này đã được sử dụng");
                    }else{
                        tvError.setText("");
                        mPhoneFragmentListener.verifyClick(phoneNumber);
                    }
                }else{
                    tvError.setText("Kết nối thất bại");
                }
            }
        });

    }
}