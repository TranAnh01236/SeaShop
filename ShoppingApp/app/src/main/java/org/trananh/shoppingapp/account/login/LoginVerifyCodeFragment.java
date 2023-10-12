package org.trananh.shoppingapp.account.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.trananh.shoppingapp.R;

import java.util.concurrent.Executor;

public class LoginVerifyCodeFragment extends Fragment {

    public static final String TAG = LoginVerifyCodeFragment.class.getName();

    private LoginActivity mLoginActivity;
    private View rootView;

    private EditText txt_1, txt_2, txt_3, txt_4, txt_5, txt_6;
    
    private String code;

    private Button btnVerify;

    private String phoneNumber, verifyCode;

    private FirebaseAuth mAuth;
    public LoginVerifyCodeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login_verify_code, container, false);
        mLoginActivity = (LoginActivity)getActivity();
        initialize();
        return rootView;
    }

    private void initialize(){
        Bundle bundle = getArguments();
        if (bundle != null){
            phoneNumber = (String) bundle.get("string_phone_number");
            verifyCode = (String) bundle.get("string_verify_code");
            if (phoneNumber != null && verifyCode != null){
                mAuth = FirebaseAuth.getInstance();
                txt_1 = rootView.findViewById(R.id.txt_code_1);
                txt_2 = rootView.findViewById(R.id.txt_code_2);
                txt_3 = rootView.findViewById(R.id.txt_code_3);
                txt_4 = rootView.findViewById(R.id.txt_code_4);
                txt_5 = rootView.findViewById(R.id.txt_code_5);
                txt_6 = rootView.findViewById(R.id.txt_code_6);

                btnVerify = rootView.findViewById(R.id.btn_verify);
                btnVerify.setClickable(false);

                txt_1.requestFocus();

                txt_1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (!charSequence.toString().trim().isEmpty()){
                            txt_2.requestFocus();
                            checkLogin();
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                txt_2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (!charSequence.toString().trim().isEmpty()){
                            txt_3.requestFocus();
                            checkLogin();
                        }
                        else
                            txt_1.requestFocus();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                txt_3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (!charSequence.toString().trim().isEmpty()){
                            txt_4.requestFocus();
                            checkLogin();
                        }
                        else
                            txt_2.requestFocus();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                txt_4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (!charSequence.toString().trim().isEmpty()) {
                            txt_5.requestFocus();
                            checkLogin();
                        }
                        else
                            txt_3.requestFocus();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                txt_5.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (!charSequence.toString().trim().isEmpty()){
                            txt_6.requestFocus();
                            checkLogin();
                        }
                        else
                            txt_4.requestFocus();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                txt_6.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().trim().isEmpty())
                            txt_5.requestFocus();
                        else
                            checkLogin();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });

                btnVerify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login();
                    }
                });
            }
        }

    }

    private void checkLogin(){
        code = "";
        code += txt_1.getText().toString();
        code += txt_2.getText().toString();
        code += txt_3.getText().toString();
        code += txt_4.getText().toString();
        code += txt_5.getText().toString();
        code += txt_6.getText().toString();
        if (code.length() == 6){
            btnVerify.setBackgroundResource(R.color.primaryColor);
            btnVerify.setTextColor(getResources().getColor(R.color.white));
            btnVerify.setClickable(true);
            login();
        }else{

            btnVerify.setBackgroundResource(R.color.light_gray);
            btnVerify.setTextColor(getResources().getColor(R.color.dark_gray));
            btnVerify.setClickable(false);

        }
    }

    private void login(){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyCode, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mLoginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            Toast.makeText(mLoginActivity, "Login Success", Toast.LENGTH_SHORT).show();

                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("key_phone_number", phoneNumber);
                            mLoginActivity.setResult(Activity.RESULT_OK, resultIntent);
                            mLoginActivity.finish();

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            }
                        }
                    }
                });
    }

}