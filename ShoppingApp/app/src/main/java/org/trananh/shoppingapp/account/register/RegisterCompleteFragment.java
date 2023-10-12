package org.trananh.shoppingapp.account.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.controller.AuthController;
import org.trananh.shoppingapp.controller.UserController;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.MyHttpResponse;

public class RegisterCompleteFragment extends Fragment {
    public static final String TAG = RegisterCompleteFragment.class.getName();
    private RegisterActivity mRegisterActivity;
    private View rootView;
    private LinearLayout linearLayoutConfirm;
    private User user;
    private AuthController mAuthController;
    private RegisterCompleteFragmentListener mRegisterCompleteFragmentListener;
    public interface RegisterCompleteFragmentListener{
        void confirmClick(User user);
    }
    public RegisterCompleteFragment(RegisterCompleteFragmentListener listener) {
        this.mRegisterCompleteFragmentListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRegisterActivity = (RegisterActivity)getActivity();
        rootView = inflater.inflate(R.layout.fragment_register_complete, container, false);
        initialize();
        return rootView;
    }

    private void initialize(){
        Bundle bundle = getArguments();
        if (bundle != null) {
            user = (User) bundle.getSerializable("object_user");
            if (user != null) {

                mAuthController = new AuthController();
                linearLayoutConfirm = rootView.findViewById(R.id.linear_layout_go_home);

                linearLayoutConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirm();
                    }
                });

            }
        }
    }

    private void confirm(){

        Log.e(TAG, user.toString());
        user.setType(2);

        mRegisterCompleteFragmentListener.confirmClick(user);

    }
}