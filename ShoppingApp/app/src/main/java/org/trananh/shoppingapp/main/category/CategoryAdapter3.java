package org.trananh.shoppingapp.main.category;

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

public class CategoryAdapter3 extends RecyclerView.Adapter<CategoryAdapter3.CategoryViewHolder>{
    private Context mContext;
    private List<StructureValue> mCategories;
    private CategoryItemListener mCategoryItemListener;
    private int single_selection_position = -1;
    public interface CategoryItemListener{
        void onClick(StructureValue category);
    }
    public void setData(List<StructureValue> list){
        this.mCategories = list;
        notifyDataSetChanged();
    }
    public CategoryAdapter3(Context mContext, CategoryItemListener listener) {
        this.mContext = mContext;
        this.mCategoryItemListener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_3, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        StructureValue category = mCategories.get(position);
        if (category == null)
            return;
        if (category.getId().trim().equals("TATCA")){
            holder.imgCat.setImageResource(R.drawable.tatca);
        }else{
            Glide.with(mContext).load(category.getImageUrl()).into(holder.imgCat);
        }
        holder.tvCat.setText(category.getValue());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCategoryItemListener.onClick(category);
                setSingleSelection(holder.getAdapterPosition());
            }
        });

        if (single_selection_position == position){
            holder.relativeLayout.setBackgroundResource(R.drawable.custom_item_category_2_hightlight);
        }else{
            holder.relativeLayout.setBackgroundResource(R.drawable.custom_item_category_2);
        }

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

    public void setSingleSelection(int adapterPosition){
        if (adapterPosition != RecyclerView.NO_POSITION){
            notifyItemChanged(single_selection_position);
            single_selection_position = adapterPosition;
            notifyItemChanged(single_selection_position);
        }
    }

}
