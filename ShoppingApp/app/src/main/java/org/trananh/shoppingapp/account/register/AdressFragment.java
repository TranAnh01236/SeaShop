package org.trananh.shoppingapp.account.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.reflect.TypeToken;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.controller.AddressController;
import org.trananh.shoppingapp.model.StructureValue;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.Constants;
import org.trananh.shoppingapp.util.MyHttpResponse;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.util.ArrayList;
import java.util.List;

public class AdressFragment extends Fragment {
    public static final String TAG = AdressFragment.class.getName();
    private RegisterActivity mRegisterActivity;
    private View rootView;
    private User user;
    private AutoCompleteTextView autoCompleteTextView1, autoCompleteTextView2, autoCompleteTextView3;
    private EditText txtAddress;
    private LinearLayout linearLayoutConfirm;
    private ArrayAdapter<StructureValue> adapter1, adapter2, adapter3;
    private List<StructureValue> lst, lst1, lst2, lst3;
    private AddressController mAddressController;
    private StructureValue ad1, ad2, ad3;
    private AddressFragmentListener mAddressFragmentListener;
    public interface AddressFragmentListener{
        void confirmClick(User user);
    }
    public AdressFragment(AddressFragmentListener listener) {
        this.mAddressFragmentListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRegisterActivity = (RegisterActivity)getActivity();
        rootView = inflater.inflate(R.layout.fragment_adress, container, false);
        initialize();
        return rootView;
    }

    private void initialize(){
        Bundle bundle = getArguments();
        if (bundle != null) {
            user = (User) bundle.getSerializable("object_user");
            if (user != null) {

                lst = new ArrayList<>();
                lst1 = new ArrayList<>();
                lst2 = new ArrayList<>();
                lst3 = new ArrayList<>();
                autoCompleteTextView1 = rootView.findViewById(R.id.auto_complete_txt_1);
                autoCompleteTextView2 = rootView.findViewById(R.id.auto_complete_txt_2);
                autoCompleteTextView3 = rootView.findViewById(R.id.auto_complete_txt_3);
                txtAddress = rootView.findViewById(R.id.edit_text_address);
                linearLayoutConfirm = rootView.findViewById(R.id.linear_layout_accept);
                mAddressController = new AddressController();

                linearLayoutConfirm.setClickable(false);

                loadList();

                for (StructureValue s : lst){
                    if (s.getLevel() == 1)
                        lst1.add(s);
                }

                adapter1 = new ArrayAdapter<>(mRegisterActivity, R.layout.list_item, lst1);
                autoCompleteTextView1.setAdapter(adapter1);

                autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        StructureValue s = lst1.get(i);
                        lst2 = new ArrayList<>();
                        for (StructureValue st : lst){
                            if ( st.getLevel() == 2 && st.getParentId().toString().trim().equals(s.getId().toString().trim()) ){
                                lst2.add(st);
                            }
                        }
                        for (StructureValue str : lst2){
                            Log.e(TAG, str.toString());
                        }
                        adapter2 = new ArrayAdapter<>(mRegisterActivity, R.layout.list_item, lst2);
                        autoCompleteTextView2.setAdapter(adapter2);
                        autoCompleteTextView2.setText("");
                        autoCompleteTextView3.setText("");
                        ad1 = s;
                        ad2 = null;
                        ad3 = null;
                        enableConfirm();
                    }
                });

                autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        StructureValue s = lst2.get(i);
                        lst3 = new ArrayList<>();
                        for (StructureValue st : lst){
                            if ( st.getLevel() == 3 && st.getParentId().toString().trim().equals(s.getId().toString().trim()) ){
                                lst3.add(st);
                            }
                        }
                        adapter3 = new ArrayAdapter<>(mRegisterActivity, R.layout.list_item, lst3);
                        autoCompleteTextView3.setAdapter(adapter3);
                        autoCompleteTextView3.setText("");
                        ad2 = s;
                        ad3 = null;
                        enableConfirm();
                    }
                });
                autoCompleteTextView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        StructureValue s = lst3.get(i);
                        ad3 = s;
                        enableConfirm();
                    }
                });
                linearLayoutConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirm();
                    }
                });
                txtAddress.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enableConfirm();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });
            }
        }
    }

    private void loadList(){
        MyHttpResponseArray myHttpResponseArray = mAddressController.getAll();
        if (myHttpResponseArray != null){
            lst = Constants.gson.fromJson(myHttpResponseArray.payloadJSON(), new TypeToken<List<StructureValue>>() {}.getType());
        }
        if (lst == null)
            lst = new ArrayList<>();
    }

    private void enableConfirm(){
        String address = txtAddress.getText().toString().trim();
        String txt1 = autoCompleteTextView1.getText().toString().trim();
        String txt2 = autoCompleteTextView2.getText().toString().trim();
        String txt3 = autoCompleteTextView3.getText().toString().trim();
        if (!address.equals("") && !txt1.equals("") && !txt2.equals("") && !txt3.equals("")){
            if (ad1 != null && ad2 != null && ad3 != null){
                linearLayoutConfirm.setClickable(true);
                linearLayoutConfirm.setBackgroundResource(R.drawable.custom_btn_addcart_hightlight);
            }else{
                linearLayoutConfirm.setClickable(false);
                linearLayoutConfirm.setBackgroundResource(R.drawable.custom_btn_addcart);
            }
        }else{
            linearLayoutConfirm.setClickable(false);
            linearLayoutConfirm.setBackgroundResource(R.drawable.custom_btn_addcart);
        }
    }

    private void confirm(){
        String address = txtAddress.getText().toString().trim();
        String txt1 = autoCompleteTextView1.getText().toString().trim();
        String txt2 = autoCompleteTextView2.getText().toString().trim();
        String txt3 = autoCompleteTextView3.getText().toString().trim();
        if (!address.equals("") && !txt1.equals("") && !txt2.equals("") && !txt3.equals("")) {
            if (ad1 != null && ad2 != null && ad3 != null) {
                user.setAddress(ad3);
                user.setAddressDetail(address);
                mAddressFragmentListener.confirmClick(user);
            }
        }
    }

}