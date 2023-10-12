package org.trananh.shoppingapp.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.data_local.DataLocalManager;

public class SettingActivity extends AppCompatActivity {

    public static final String TAG = SettingActivity.class.getName();


    private LinearLayout lnAccount;
    private RelativeLayout rlAccountSetting, rlSecuritySetting, rlDeleteAccount;

    private Button btnLogout;
    private ImageButton btnBack;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initialize();
    }

    private void initialize(){

        btnBack = findViewById(R.id.img_btn_back);

        lnAccount = findViewById(R.id.linearLayout_account);
        rlAccountSetting = findViewById(R.id.relativeLayout_account_setting);
        rlSecuritySetting = findViewById(R.id.relativeLayout_security_setting);
        rlDeleteAccount = findViewById(R.id.relativeLayout_request_delete_account);

        btnLogout = findViewById(R.id.btn_logout);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        User user = DataLocalManager.getUser();
        if (user == null){
            lnAccount.setVisibility(View.GONE);
            rlAccountSetting.setVisibility(View.GONE);
            rlSecuritySetting.setVisibility(View.GONE);
            rlDeleteAccount.setVisibility(View.GONE);
            btnLogout.setVisibility(View.GONE);
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    private void logout(){

        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        DataLocalManager.deleteUser();
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}