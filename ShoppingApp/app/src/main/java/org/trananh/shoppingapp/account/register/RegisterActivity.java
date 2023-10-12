package org.trananh.shoppingapp.account.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.account.login.LoginActivity;
import org.trananh.shoppingapp.account.login.LoginVerifyCodeFragment;
import org.trananh.shoppingapp.controller.AuthController;
import org.trananh.shoppingapp.model.User;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getName();

    private FirebaseAuth mAuth;

    private ImageButton btnBack;
    private int fragmentPosition = 0;
    private User currentUser;

    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
    }

    private void initialize(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mAuth = FirebaseAuth.getInstance();
        authController = new AuthController();

        PhoneFragment mPhoneFragment = new PhoneFragment(new PhoneFragment.PhoneFragmentListener() {
            @Override
            public void verifyClick(String phoneNumber) {
                String phoneNumber1 = phoneNumber.trim().substring(1);

                goToLoginNamePasswordFragment("+84" + phoneNumber1);

//                verifyPhoneNumber("+84" + phoneNumber1);

            }
        });

        fragmentPosition = 0;

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .add(R.id.frame_layout, mPhoneFragment)
                .commit();

    }

    private void goToVerifyCodeFragment(String phoneNumber, String verifyCode){
        fragmentPosition = 1;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        RegisterVerifyCodeFragment mRegisterVerifyCodeFragment = new RegisterVerifyCodeFragment(new RegisterVerifyCodeFragment.RegisterVerifyFragmentListener() {
            @Override
            public void onVerifyClick(String phoneNumber) {
                goToLoginNamePasswordFragment(phoneNumber);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("string_phone_number", phoneNumber);
        bundle.putString("string_verify_code", verifyCode);
        mRegisterVerifyCodeFragment.setArguments(bundle);
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frame_layout, mRegisterVerifyCodeFragment);
        fragmentTransaction.addToBackStack(mRegisterVerifyCodeFragment.TAG);
        fragmentTransaction.commit();
    }

    private void goToLoginNamePasswordFragment(String phoneNumber){
        fragmentPosition = 2;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        LoginNamePasswordFragment mLoginNamePasswordFragment = new LoginNamePasswordFragment(new LoginNamePasswordFragment.LoginNamePasswordFragmentListener() {
            @Override
            public void confirmClick(User user) {
                currentUser = user;
                goToInfoAccountFragment(user);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("string_phone_number", phoneNumber);
        mLoginNamePasswordFragment.setArguments(bundle);
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frame_layout, mLoginNamePasswordFragment);
        fragmentTransaction.addToBackStack(mLoginNamePasswordFragment.TAG);
        fragmentTransaction.commit();

    }

    private void goToInfoAccountFragment(User user){
        fragmentPosition = 3;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        InfoAccountFragment mInfoAccountFragment = new InfoAccountFragment(new InfoAccountFragment.InfoAccountFragmentListener() {
            @Override
            public void confirmClick(User user) {
                currentUser = user;
                goToAddressFragment(user);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);
        mInfoAccountFragment.setArguments(bundle);
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frame_layout, mInfoAccountFragment);
        fragmentTransaction.addToBackStack(mInfoAccountFragment.TAG);
        fragmentTransaction.commit();
    }

    private void goToAddressFragment(User user){
        fragmentPosition = 4;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        AdressFragment mAdressFragment = new AdressFragment(new AdressFragment.AddressFragmentListener() {
            @Override
            public void confirmClick(User user) {
                currentUser = user;
                goToOptionalInfoFragment(user);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);
        mAdressFragment.setArguments(bundle);
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frame_layout, mAdressFragment);
        fragmentTransaction.addToBackStack(mAdressFragment.TAG);
        fragmentTransaction.commit();

    }

    private void goToOptionalInfoFragment(User user){
        fragmentPosition = 5;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        OptionalInfoFragment mOptionalInfoFragment = new OptionalInfoFragment(new OptionalInfoFragment.OptionalInfoFragmentListener() {
            @Override
            public void confirmClick(User user) {
                currentUser = user;
                goToRegisterCompleteFragment(user);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);
        mOptionalInfoFragment.setArguments(bundle);
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frame_layout, mOptionalInfoFragment);
        fragmentTransaction.addToBackStack(mOptionalInfoFragment.TAG);
        fragmentTransaction.commit();

    }

    private void goToRegisterCompleteFragment(User user){
        fragmentPosition = 6;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        RegisterCompleteFragment mRegisterCompleteFragment = new RegisterCompleteFragment(new RegisterCompleteFragment.RegisterCompleteFragmentListener() {
            @Override
            public void confirmClick(User user) {
                completeRegister("+84" + user.getPhoneNumber().substring(1));
            }
        });
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);
        mRegisterCompleteFragment.setArguments(bundle);
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frame_layout, mRegisterCompleteFragment);
        fragmentTransaction.addToBackStack(mRegisterCompleteFragment.TAG);
        fragmentTransaction.commit();
    }

    private void verifyPhoneNumber(String phoneNumber){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential, phoneNumber);
                            }
                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Log.e(TAG, "Verify Failed!!!");
                            }
                            @Override
                            public void onCodeSent(@NonNull String verifyCode, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifyCode, forceResendingToken);
                                goToVerifyCodeFragment(phoneNumber, verifyCode);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential, String phoneNumber) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e(TAG, "Login Success");

                            FirebaseUser user = task.getResult().getUser();

                            goToLoginNamePasswordFragment(phoneNumber);

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            }
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (fragmentPosition == 2 || fragmentPosition == 3 || fragmentPosition == 4){
            openRegisterCancelDialog();
        } else if (fragmentPosition == 5){
            goToRegisterCompleteFragment(currentUser);
        } else if (fragmentPosition == 6){
            completeRegister("+84" + currentUser.getPhoneNumber().substring(1));
        } else{
            super.onBackPressed();
        }
    }

    private void completeRegister(String phone){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("key_phone_number", phone);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private void openRegisterCancelDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_cancel_register);

        Window window = dialog.getWindow();
        if (window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(false);

        RelativeLayout rlYes = dialog.findViewById(R.id.relative_layout_yes);
        RelativeLayout rlNo = dialog.findViewById(R.id.relative_layout_no);

        rlNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        rlYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        dialog.show();
    }
}