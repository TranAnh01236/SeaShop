package org.trananh.shoppingapp.account.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.account.register.RegisterActivity;
import org.trananh.shoppingapp.controller.AuthController;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.Constants;
import org.trananh.shoppingapp.util.MyHttpResponse;

public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getName();

    private LoginActivity mLoginActivity;
    private View rootView;

    private EditText txtAccount, txtPassword;

    private TextView tvShow;

    private Button btnLogin;

    private TextView tvRegister;

    private LoginFragmentListener mLoginFragmentListener;
    private ProgressBar progressBar;

    private AuthController authController;

    public interface LoginFragmentListener{
        void btnLoginClick(User user);
    }

    public LoginFragment(LoginFragmentListener listener) {
        this.mLoginFragmentListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mLoginActivity = (LoginActivity) getActivity();
        initialize();
        return rootView;
    }

    private void initialize(){
        authController = new AuthController();
        txtAccount = rootView.findViewById(R.id.txt_account);
        txtPassword = rootView.findViewById(R.id.txt_password);
        btnLogin = rootView.findViewById(R.id.btn_login);
        tvShow = rootView.findViewById(R.id.tv_show_password);
        tvRegister = rootView.findViewById(R.id.text_view_register);

        progressBar = rootView.findViewById(R.id.progressBar);

        btnLogin.setClickable(false);

        txtAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showLoginButton();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showLoginButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvShow.getText().toString().equals(getResources().getString(R.string.show_password))){
                    txtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    tvShow.setText(getResources().getString(R.string.hide_password));
                }else{
                    txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    tvShow.setText(getResources().getString(R.string.show_password));
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mLoginActivity, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void showLoginButton(){
        if (!txtAccount.getText().toString().trim().equals("")&&!txtPassword.getText().toString().trim().equals("")){
            btnLogin.setClickable(true);
            btnLogin.setBackgroundResource(R.color.primaryColor);
            btnLogin.setTextColor(getResources().getColor(R.color.white));
        }else{
            btnLogin.setClickable(false);
            btnLogin.setBackgroundResource(R.color.light_gray);
            btnLogin.setTextColor(getResources().getColor(R.color.dark_gray));
        }
    }

    private void login(){
        String account = txtAccount.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        MyHttpResponse myHttpResponse = authController.login(account, password);
        if (myHttpResponse != null){
            User user = Constants.gson.fromJson(myHttpResponse.payloadJSON(), User.class);
            if(user != null){
                Log.e(TAG, user.toString());
                mLoginFragmentListener.btnLoginClick(user);
            }
        }
    }

}