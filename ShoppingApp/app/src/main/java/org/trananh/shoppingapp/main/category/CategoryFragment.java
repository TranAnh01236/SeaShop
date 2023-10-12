package org.trananh.shoppingapp.main.category;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.controller.CategoryController;
import org.trananh.shoppingapp.main.MainActivity;
import org.trananh.shoppingapp.main.SearchActivity;
import org.trananh.shoppingapp.main.category.product.ProductActivity;
import org.trananh.shoppingapp.model.StructureValue;
import org.trananh.shoppingapp.util.Constants;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private MainActivity mMainActivity;
    private View rootView;
    private RecyclerView mRecyclerView, mRecycleView2;
    private CategoryAdapter1 mCategoryAdapter1;
    private CategoryAdapter2 mCategoryAdapter2;
    private CategoryController mCategoryController;
    private List<StructureValue> mListCategories;
    private CategoryItemListener mCategoryItemListener;
    public interface CategoryItemListener{
        void onClick(StructureValue category);
    }

    public CategoryFragment() {
    }
    public CategoryFragment(CategoryItemListener listener){
        this.mCategoryItemListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainActivity = (MainActivity) getActivity();
        rootView = inflater.inflate(R.layout.fragment_category, container, false);
        initialize();
        return rootView;
    }
    private void initialize(){
        mCategoryController = new CategoryController();
        mListCategories = new ArrayList<>();
        getListCategory();
        mRecyclerView = rootView.findViewById(R.id.recycle_view_category);
        mRecycleView2 = rootView.findViewById(R.id.recycle_view_category_2);

        mCategoryAdapter2 = new CategoryAdapter2(mMainActivity, new CategoryAdapter2.CategoryItemListener() {
            @Override
            public void onClick(StructureValue category) {
                mCategoryItemListener.onClick(category);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mMainActivity, 3);
        mRecycleView2.setLayoutManager(gridLayoutManager);
        mRecycleView2.setAdapter(mCategoryAdapter2);

        List<StructureValue> lstFirst = getFirstCategory();
        mCategoryAdapter2.setData(lstFirst);

        mCategoryAdapter1 = new CategoryAdapter1(mMainActivity, new CategoryAdapter1.CategoryItemListener() {
            @Override
            public void onClick(StructureValue category) {
                List<StructureValue> lst = new ArrayList<>();
                for(StructureValue category1 : mListCategories){
                    if (category1.getParentId() != null && (category1.getParentId().trim().equals(category.getId().trim()))){
                        lst.add(category1);
                    }
                }
                mCategoryAdapter2.setData(lst);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        if (mListCategories != null){
            List<StructureValue> lst = new ArrayList<>();
            for(StructureValue category : mListCategories){
                if (category.getLevel() == 0)
                    lst.add(category);
            }
            mCategoryAdapter1.setData(lst);
            mCategoryAdapter1.setSingleSelection(0);
            mRecyclerView.setAdapter(mCategoryAdapter1);
        }

    }

    private void getListCategory(){
        MyHttpResponseArray myHttpResponseArray = mCategoryController.getAll();
        if (myHttpResponseArray != null) {
            mListCategories = Constants.gson.fromJson(myHttpResponseArray.payloadJSON(), new TypeToken<List<StructureValue>>() {}.getType());
        }
    }

    private List<StructureValue> getFirstCategory(){
        for (StructureValue value : mListCategories){
            if (value.getLevel() == 0){
                List<StructureValue> lst = new ArrayList<>();
                MyHttpResponseArray rs = mCategoryController.getByParentId(value.getId().trim());
                if (rs != null){
                    lst = Constants.gson.fromJson(rs.payloadJSON(), new TypeToken<List<StructureValue>>(){}.getType());
                }
                if (lst == null)
                    lst = new ArrayList<>();
                return lst;
            }
        }
        return new ArrayList<>();
    }
}