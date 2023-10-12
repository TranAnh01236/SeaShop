package org.trananh.shoppingapp.main.category.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.controller.CategoryController;
import org.trananh.shoppingapp.controller.ProductController;
import org.trananh.shoppingapp.controller.UnitOfMeasureController;
import org.trananh.shoppingapp.main.category.CategoryAdapter3;
import org.trananh.shoppingapp.model.HierarchyStructure;
import org.trananh.shoppingapp.model.StructureValue;
import org.trananh.shoppingapp.model.Product;
import org.trananh.shoppingapp.model.UnitOfMeasure;
import org.trananh.shoppingapp.util.Constants;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    public static String TAG = ProductActivity.class.getName();

    private RecyclerView mRecyclerView, mRecycleViewProduct;
    private CategoryAdapter3 mCategoryAdapter3;
    private ProductAdapter productAdapter;
    private StructureValue mainCategory;
    private List<StructureValue> mListCategories;
    private List<Product> mListProducts;
    private List<UnitOfMeasure> mListUnitOfMeasure;
    private CategoryController categoryController;
    private ImageButton btnBack;
    private ProductController mProductController;
    private UnitOfMeasureController mUnitOfMeasureController;
    private TextView tvSearch;
    private RelativeLayout layoutNull;
    private View layoutView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initialize();
    }

    private void initialize(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (getIntent().getExtras() != null){
            mainCategory = (StructureValue) getIntent().getExtras().get("object_category");
            if (mainCategory!=null){
                mProductController = new ProductController();
                mUnitOfMeasureController = new UnitOfMeasureController();
                btnBack = findViewById(R.id.img_btn_back);
                mRecyclerView = findViewById(R.id.recycle_view);
                mRecycleViewProduct = findViewById(R.id.recycle_view_product);
                tvSearch = findViewById(R.id.tv_search);
                layoutNull = findViewById(R.id.layout_null);
                layoutView = findViewById(R.id.view_layout);

                categoryController = new CategoryController();
                getListCategories();

                tvSearch.setText(mainCategory.getValue());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(linearLayoutManager);

                productAdapter = new ProductAdapter(this, new ProductAdapter.ProductItemListener() {
                    @Override
                    public void onClick(Product product) {
                        Intent intent = new Intent(ProductActivity.this, ProductDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("object_product", product);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onClickCart(Product product) {
                        Toast.makeText(ProductActivity.this, "Click cart", Toast.LENGTH_SHORT).show();
                    }
                });
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                mRecycleViewProduct.setLayoutManager(gridLayoutManager);

                getListProducts(new StructureValue("TATCA"));
                productAdapter.setData(mListProducts);
                showListProduct();
                mRecycleViewProduct.setAdapter(productAdapter);

                mCategoryAdapter3 = new CategoryAdapter3(this, new CategoryAdapter3.CategoryItemListener() {
                    @Override
                    public void onClick(StructureValue category) {
                        getListProducts(category);
                        productAdapter.setData(mListProducts);
                        showListProduct();
                    }
                });
                mCategoryAdapter3.setData(mListCategories);
                mCategoryAdapter3.setSingleSelection(0);
                showListCategory();
                mRecyclerView.setAdapter(mCategoryAdapter3);

                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });

            }
        }
    }
    private void getListCategories(){
        MyHttpResponseArray myHttpResponseArray = categoryController.getByParentId(mainCategory.getId());
        if (myHttpResponseArray != null) {
            mListCategories = Constants.gson.fromJson(myHttpResponseArray.payloadJSON(), new TypeToken<List<StructureValue>>() {}.getType());
        }

        if (mListCategories == null)
            mListCategories = new ArrayList<>();
        else {
            if (mListCategories.size() > 1){
                StructureValue category = new StructureValue("TATCA", "", "", 0, "", "", new HierarchyStructure(2));
                mListCategories.add(0, category);
            }
        }
    }
    private void getListProducts(StructureValue category){
        mListProducts = new ArrayList<>();
        if (category.getId().trim().equals("TATCA")){
            MyHttpResponseArray myHttpResponseArray = mProductController.getAll();
            if (myHttpResponseArray != null) {
                ArrayList<Product> lstProducts = Constants.gson.fromJson(myHttpResponseArray.payloadJSON(), new TypeToken<List<Product>>() {}.getType());
                if (lstProducts != null){
                    for (StructureValue value : mListCategories){
                        for (Product p : lstProducts){
                            if (p.getCategory().getId().trim().equals(value.getId().trim())){
                                mListProducts.add(p);
                            }
                        }
                    }
                }
                if (mListCategories == null || mListCategories.size() == 0){
                    MyHttpResponseArray myHttpResponseArray1 = mProductController.getByCategory(mainCategory.getId().trim());
                    if (myHttpResponseArray1 != null){
                        mListProducts = Constants.gson.fromJson(myHttpResponseArray1.payloadJSON(), new TypeToken<List<Product>>() {}.getType());
                    }
                }
            }
        }else{
            MyHttpResponseArray myHttpResponseArray = mProductController.getByCategory(category.getId().trim());
            if (myHttpResponseArray != null) {
                mListProducts = Constants.gson.fromJson(myHttpResponseArray.payloadJSON(), new TypeToken<List<Product>>() {}.getType());
            }
        }
        if (mListProducts == null)
            mListProducts = new ArrayList<>();
    }

    private void showListProduct(){
        if (mListProducts == null || mListProducts.size() == 0){
            layoutNull.setVisibility(View.VISIBLE);
            mRecycleViewProduct.setVisibility(View.GONE);
        }else{
            layoutNull.setVisibility(View.GONE);
            mRecycleViewProduct.setVisibility(View.VISIBLE);
        }
    }

    private void showListCategory(){
        if (mListCategories == null || mListCategories.size() == 0){
            layoutView.setVisibility(View.GONE);
        }else
            layoutView.setVisibility(View.VISIBLE);
    }
}