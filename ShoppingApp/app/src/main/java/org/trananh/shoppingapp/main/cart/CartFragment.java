package org.trananh.shoppingapp.main.cart;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.controller.CategoryController;
import org.trananh.shoppingapp.main.MainActivity;
import org.trananh.shoppingapp.main.SearchActivity;
import org.trananh.shoppingapp.model.StructureValue;
import org.trananh.shoppingapp.util.Constants;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    private View rootView;

    private MainActivity mMainActivity;
    private RelativeLayout mRelativeLayoutSearch;
    private CategoryController mCategoryController;
    private RecyclerView mRecyclerViewCategory;
    private CartCategoryAdapter mCartCategoryAdapter;

    private List<StructureValue> mCategoriesList;
    private RelativeLayout btnCategory;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        mMainActivity = (MainActivity) getActivity();
        initialize();

        return rootView;
    }

    private void initialize(){

        mRelativeLayoutSearch = rootView.findViewById(R.id.relativeLayout_search);

        mRelativeLayoutSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mMainActivity, SearchActivity.class);
                startActivity(intent);
            }
        });

        mCategoryController = new CategoryController();
        mRecyclerViewCategory = rootView.findViewById(R.id.recycle_view_category);
        btnCategory = rootView.findViewById(R.id.relative_layout_category);

        getListCategories();

        mCartCategoryAdapter = new CartCategoryAdapter(mMainActivity, new CartCategoryAdapter.CategoryItemListener() {
            @Override
            public void onClick(StructureValue category) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity, RecyclerView.HORIZONTAL, false);
        mRecyclerViewCategory.setLayoutManager(linearLayoutManager);

        mRecyclerViewCategory.setAdapter(mCartCategoryAdapter);
        mCartCategoryAdapter.setData(mCategoriesList);

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivity.chooseCategoryFragment();
            }
        });

    }

    private void getListCategories(){
        MyHttpResponseArray myHttpResponseArray = mCategoryController.getByTypeAndLevel(2,2);
        if (myHttpResponseArray != null) {
            mCategoriesList = Constants.gson.fromJson(myHttpResponseArray.payloadJSON(), new TypeToken<List<StructureValue>>() {}.getType());
        }

        if (mCategoriesList == null)
            mCategoriesList = new ArrayList<>();
    }
}