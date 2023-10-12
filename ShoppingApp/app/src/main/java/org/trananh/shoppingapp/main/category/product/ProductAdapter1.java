package org.trananh.shoppingapp.main.category.product;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.controller.UnitOfMeasureController;
import org.trananh.shoppingapp.model.Product;
import org.trananh.shoppingapp.model.UnitOfMeasure;
import org.trananh.shoppingapp.util.Constants;
import org.trananh.shoppingapp.util.MyHttpResponseArray;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter1 extends RecyclerView.Adapter<ProductAdapter1.ProductViewHolder>{
    private Context mContext;
    private ProductItemListener mProductItemListener;
    private List<Product> mListProducts;
    private List<UnitOfMeasure> mListUnitOfMeasures;
    private UnitOfMeasureController mUnitOfMeasureController;
    public interface ProductItemListener{
        void onClick(Product product);
        void onClickCart(Product product);
    }
    public ProductAdapter1(Context context, ProductItemListener listener){
        this.mContext = context;
        this.mProductItemListener = listener;
        this.mUnitOfMeasureController = new UnitOfMeasureController();
        MyHttpResponseArray myHttpResponseArray = mUnitOfMeasureController.getAll();
        if (myHttpResponseArray != null){
            mListUnitOfMeasures = Constants.gson.fromJson(myHttpResponseArray.payloadJSON(), new TypeToken<List<UnitOfMeasure>>() {}.getType());
        }
        if (mListUnitOfMeasures == null)
            mListUnitOfMeasures = new ArrayList<>();
    }

    public void setData(List<Product> list){
        this.mListProducts = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_1, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = mListProducts.get(position);
        if (product == null)
            return;
        Glide.with(mContext).load(product.getImageUrl()).into(holder.imgProduct);
        holder.tvName.setText(product.getName().trim());

        holder.tvUnit.setText("Đơn vị");
        for (UnitOfMeasure uom : mListUnitOfMeasures){
            if (uom.getProduct().getId().trim().equals(product.getId().trim()) && uom.getQuantity() == 1){
                holder.tvUnit.setText("Đơn vị: " + uom.getBaseUnitOfMeasure().getValue());
            }
        }


        holder.tvPrice.setText("Giá:");
        holder.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProductItemListener.onClickCart(product);
            }
        });
        holder.layout_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProductItemListener.onClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListProducts!=null)
            return mListProducts.size();
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgProduct;
        private TextView tvName, tvPrice, tvUnit;
        private LinearLayout btnCart;
        private RelativeLayout layout_product;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvName = itemView.findViewById(R.id.tv_product_name);
            tvUnit = itemView.findViewById(R.id.tv_unit_product);
            tvPrice = itemView.findViewById(R.id.tv_price_product);
            btnCart = itemView.findViewById(R.id.linear_layout_cart);
            layout_product = itemView.findViewById(R.id.relative_layout_product);
        }
    }
}
