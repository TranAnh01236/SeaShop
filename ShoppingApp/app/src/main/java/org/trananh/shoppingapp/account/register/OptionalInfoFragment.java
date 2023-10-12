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

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.model.User;

public class OptionalInfoFragment extends Fragment {
    public static final String TAG = OptionalInfoFragment.class.getName();
    private RegisterActivity mRegisterActivity;
    private View rootView;
    private User user;
    private EditText txtEmail;
    private LinearLayout linearLayoutConfirm;
    private TextView tvError;
    private OptionalInfoFragmentListener mOptionalInfoFragmentListener;
    public interface OptionalInfoFragmentListener{
        void confirmClick(User user);
    }
    public OptionalInfoFragment(OptionalInfoFragmentListener listener) {
        this.mOptionalInfoFragmentListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRegisterActivity = (RegisterActivity)getActivity();
        rootView = inflater.inflate(R.layout.fragment_optional_info, container, false);
        initialize();
        return rootView;
    }

    private void initialize(){
        Bundle bundle = getArguments();
        if (bundle != null) {
            user = (User) bundle.getSerializable("object_user");
            if (user != null) {

                txtEmail = rootView.findViewById(R.id.edit_text_email);
                linearLayoutConfirm = rootView.findViewById(R.id.linear_layout_accept);
                tvError = rootView.findViewById(R.id.text_view_error);

                txtEmail.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        tvError.setText("");
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
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

    private void confirm(){
        tvError.setText("");
        String mail = txtEmail.getText().toString().trim();
        if (!mail.equals("")){
            if (mail.matches("/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/")){
                user.setEmail(mail);
            }else {
                tvError.setText("Địa chỉ mail không hợp lệ");
            }
        }
        mOptionalInfoFragmentListener.confirmClick(user);
    }
}