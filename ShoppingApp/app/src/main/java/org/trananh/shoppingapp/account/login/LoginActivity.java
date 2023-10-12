package org.trananh.shoppingapp.account.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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
import org.trananh.shoppingapp.api.ApiService;
import org.trananh.shoppingapp.controller.AuthController;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.MyHttpResponse;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getName();

    private FirebaseAuth mAuth;

    private ImageButton btnBack;
    private int fragmentPosition = 0;

    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    private void initialize(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        authController = new AuthController();

        btnBack = findViewById(R.id.img_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        LoginFragment loginFragment = new LoginFragment(new LoginFragment.LoginFragmentListener() {
            @Override
            public void btnLoginClick(User user) {
                String phoneNumber = user.getPhoneNumber().trim().substring(1);
                verifyPhoneNumber("+84" + phoneNumber);
            }
        });
        fragmentPosition = 0;

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .add(R.id.frame_layout, loginFragment)
                .commit();
    }

    private void goToLoginVerifyCodeFragment(String phoneNumber, String verifyCode){
        fragmentPosition = 1;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LoginVerifyCodeFragment loginVerifyCodeFragment = new LoginVerifyCodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("string_phone_number", phoneNumber);
        bundle.putString("string_verify_code", verifyCode);
        loginVerifyCodeFragment.setArguments(bundle);
        fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        fragmentTransaction.replace(R.id.frame_layout, loginVerifyCodeFragment);
        fragmentTransaction.addToBackStack(loginVerifyCodeFragment.TAG);
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
                                goToLoginVerifyCodeFragment(phoneNumber, verifyCode);
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
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "Login Success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI

                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("key_phone_number", phoneNumber);
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

}