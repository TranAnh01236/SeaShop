package org.trananh.shoppingapp.main.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.account.login.LoginActivity;
import org.trananh.shoppingapp.account.register.RegisterActivity;
import org.trananh.shoppingapp.controller.UserController;
import org.trananh.shoppingapp.main.MainActivity;
import org.trananh.shoppingapp.main.SettingActivity;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.Constants;
import org.trananh.shoppingapp.util.MyHttpResponse;
import org.trananh.shoppingapp.util.data_local.DataLocalManager;

public class AccountFragment extends Fragment {

    public static final String TAG = AccountFragment.class.getName();
    private MainActivity mMainActivity;
    private View rootView;

    private Button btnLogin, btnRegister;

    private ImageButton btnSetting;

    private UserController mUserController;
    private TextView txtUserName, txtLoginName;
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        String phoneNumber = intent.getStringExtra("key_phone_number");
                        if (phoneNumber != null && !phoneNumber.equals("")){
                            String phoneNumber1 = "0" + phoneNumber.substring(3);
                            MyHttpResponse myHttpResponse = mUserController.getUserByPhoneNumber(phoneNumber1);
                            if (myHttpResponse != null){
                                User user = Constants.gson.fromJson(myHttpResponse.payloadJSON(), User.class);
                                if (user != null){
                                    DataLocalManager.setUser(user);
                                    mMainActivity.recreate();
                                }
                            }
                        }

                    }
                }
            });

    private ActivityResultLauncher<Intent> mActivityResultLauncher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        mMainActivity.recreate();
                    }
                }
            });

    public AccountFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_account, container, false);
        mMainActivity = (MainActivity) getActivity();
        initialize();
        return rootView;
    }

    private void initialize(){
        btnLogin = rootView.findViewById(R.id.btn_login);
        btnRegister = rootView.findViewById(R.id.btn_register);

        txtUserName = rootView.findViewById(R.id.txt_user_name);
        txtLoginName = rootView.findViewById(R.id.txt_login_name);

        btnSetting = rootView.findViewById(R.id.img_btn_setting);

        mUserController = new UserController();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mMainActivity, LoginActivity.class);
                mActivityResultLauncher.launch(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mMainActivity, RegisterActivity.class);
                mActivityResultLauncher.launch(intent);
            }
        });

        User user = DataLocalManager.getUser();
        if (user != null){
            txtUserName.setText(user.getFirstName() + " " + user.getLastName());
            txtLoginName.setText(user.getLoginName());
            txtUserName.setVisibility(View.VISIBLE);
            txtLoginName.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
            btnRegister.setVisibility(View.GONE);
        }else{
            txtUserName.setText("");
            txtLoginName.setText("");
            txtUserName.setVisibility(View.GONE);
            txtLoginName.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.VISIBLE);
        }

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mMainActivity, SettingActivity.class);
                mActivityResultLauncher2.launch(intent);
            }
        });
    }
}