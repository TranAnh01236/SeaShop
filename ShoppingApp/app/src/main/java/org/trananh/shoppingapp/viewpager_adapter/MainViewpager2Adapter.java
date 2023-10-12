package org.trananh.shoppingapp.viewpager_adapter;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.trananh.shoppingapp.main.account.AccountFragment;
import org.trananh.shoppingapp.main.cart.CartFragment;
import org.trananh.shoppingapp.main.category.CategoryFragment;
import org.trananh.shoppingapp.main.category.product.ProductActivity;
import org.trananh.shoppingapp.main.home.HomeFragment;
import org.trananh.shoppingapp.main.promotion.PromotionFragment;
import org.trananh.shoppingapp.model.StructureValue;

public class MainViewpager2Adapter extends FragmentStateAdapter {

    private FragmentActivity mFragmentActivity;
    public MainViewpager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.mFragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new CategoryFragment(new CategoryFragment.CategoryItemListener() {
                    @Override
                    public void onClick(StructureValue category) {
                        Intent intent = new Intent(mFragmentActivity, ProductActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("object_category", category);
                        intent.putExtras(bundle);
                        mFragmentActivity.startActivity(intent);
                    }
                });
            case 2:
                return new CartFragment();
            case 3:
                return new PromotionFragment();
            case 4:
                return new AccountFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
