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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.controller.ProductController;
import org.trananh.shoppingapp.controller.UnitOfMeasureController;
import org.trananh.shoppingapp.model.Product;
import org.trananh.shoppingapp.model.UnitOfMeasure;
import org.trananh.shoppingapp.util.Constants;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String TAG = ProductDetailActivity.class.getName();

    private ImageButton btnBack;

    private Product mainProduct;
    private List<UnitOfMeasure> mUnitOfMeasures;
    private UnitOfMeasureController mUnitOfMeasureController;
    private UnitAdapter mUnitAdapter;
    private RecyclerView mRecyclerViewUnit;
    private TextView tvName, tvDescription, tvViewAll, tvCart;
    private ImageView image;

    private RecyclerView recyclerViewProduct;
    private ProductController productController;
    private List<Product> mProducts;
    private ProductAdapter1 mProductAdapter;
    private RelativeLayout relativeLayoutCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initialize();
    }

    private void initialize(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (getIntent().getExtras() != null){
            mainProduct = (Product) getIntent().getExtras().get("object_product");
            if (mainProduct != null){

                Log.e(TAG, mainProduct.toString());

                productController = new ProductController();

                mUnitOfMeasureController = new UnitOfMeasureController();
                btnBack = findViewById(R.id.img_btn_back);
                mRecyclerViewUnit = findViewById(R.id.recycle_view_unit);
                tvName = findViewById(R.id.text_view_name);
                image = findViewById(R.id.image_view_product);
                tvDescription = findViewById(R.id.text_view_description);
                recyclerViewProduct = findViewById(R.id.recycle_view_product);
                tvViewAll = findViewById(R.id.tv_view_all);
                relativeLayoutCart = findViewById(R.id.relative_layout_button_cart);
                tvCart = findViewById(R.id.tv_cart);

                relativeLayoutCart.setClickable(false);

                tvName.setText(mainProduct.getName().trim());
                Glide.with(this).load(mainProduct.getImageUrl()).into(image);
                tvDescription.setText(mainProduct.getDescription().trim());


                getListUnitOfMeasure();
                getListProduct();

                mUnitAdapter = new UnitAdapter(this, new UnitAdapter.UnitMeasureListener() {
                    @Override
                    public void onClick(UnitOfMeasure unitOfMeasure) {
                        relativeLayoutCart.setClickable(true);
                        relativeLayoutCart.setBackgroundResource(R.drawable.custom_btn_addcart_hightlight);
                        tvCart.setTextColor(getResources().getColor(R.color.white));
                        Glide.with(ProductDetailActivity.this).load(unitOfMeasure.getImageUrl()).into(image);
                    }
                });

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
                mRecyclerViewUnit.setLayoutManager(linearLayoutManager);
                mRecyclerViewUnit.setAdapter(mUnitAdapter);

                mUnitAdapter.setData(mUnitOfMeasures);

                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });

                mProductAdapter = new ProductAdapter1(this, new ProductAdapter1.ProductItemListener() {
                    @Override
                    public void onClick(Product product) {
                        Intent intent = new Intent(ProductDetailActivity.this, ProductDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("object_product", product);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onClickCart(Product product) {

                    }
                });

                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
                recyclerViewProduct.setLayoutManager(linearLayoutManager1);
                recyclerViewProduct.setAdapter(mProductAdapter);

                mProductAdapter.setData(mProducts);

                tvViewAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ProductDetailActivity.this, ProductActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("object_category", mainProduct.getCategory());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    private void getListUnitOfMeasure(){
        MyHttpResponseArray myHttpResponseArray = mUnitOfMeasureController.getUnitsByProductId(mainProduct.getId());
        if (myHttpResponseArray != null) {
            mUnitOfMeasures = Constants.gson.fromJson(myHttpResponseArray.payloadJSON(), new TypeToken<List<UnitOfMeasure>>() {}.getType());
        }
        if (mUnitOfMeasures == null)
            mUnitOfMeasures = new ArrayList<>();
    }

    private void getListProduct(){
        MyHttpResponseArray myHttpResponseArray = productController.getByCategory(mainProduct.getCategory().getId().trim());
        if (myHttpResponseArray != null) {
            mProducts = Constants.gson.fromJson(myHttpResponseArray.payloadJSON(), new TypeToken<List<Product>>() {}.getType());
        }
        if (mProducts == null)
            mProducts = new ArrayList<>();
    }


}