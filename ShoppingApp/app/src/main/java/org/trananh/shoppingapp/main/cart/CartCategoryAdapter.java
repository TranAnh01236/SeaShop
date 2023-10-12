package org.trananh.shoppingapp.main.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.model.StructureValue;

import java.util.List;

public class CartCategoryAdapter extends RecyclerView.Adapter<CartCategoryAdapter.CategoryViewHolder>{
    private Context mContext;
    private List<StructureValue> mCategories;
    private CategoryItemListener mCategoryItemListener;
    public interface CategoryItemListener{
        void onClick(StructureValue category);
    }
    public CartCategoryAdapter(Context mContext, CategoryItemListener listener) {
        this.mContext = mContext;
        this.mCategoryItemListener = listener;
    }

    public void setData(List<StructureValue> list){
        this.mCategories = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_2, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        StructureValue category = mCategories.get(position);
        if (category == null)
            return;
        Glide.with(mContext).load(category.getImageUrl()).into(holder.imgCat);
        holder.tvCat.setText(category.getValue());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCategoryItemListener.onClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mCategories!=null)
            return mCategories.size();
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout relativeLayout;
        private ImageView imgCat;
        private TextView tvCat;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCat = itemView.findViewById(R.id.img_category);
            tvCat = itemView.findViewById(R.id.tv_category);
            relativeLayout = itemView.findViewById(R.id.item_category);
        }
    }

}
