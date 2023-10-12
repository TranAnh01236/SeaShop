package org.trananh.shoppingapp.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.controller.AuthController;
import org.trananh.shoppingapp.model.StructureValue;
import org.trananh.shoppingapp.model.User;
import org.trananh.shoppingapp.util.DepthPageTransformer;
import org.trananh.shoppingapp.util.MyHttpResponse;
import org.trananh.shoppingapp.util.data_local.DataLocalManager;
import org.trananh.shoppingapp.viewpager_adapter.MainViewpager2Adapter;

import java.sql.Date;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    private ViewPager2 mViewPager2;
    private BottomNavigationView mBottomNavigationView;

    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mAuth;

    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        AuthController authController = new AuthController();
//        MyHttpResponse rs = authController.register(new User("123324324", "le","van","levannn","12345678", "asdasdasda" , "0999999999", "asdasd@gmail.com", 1, 2, Date.valueOf("2000-9-5"), new StructureValue("XADUCHOADONG")));

        mViewPager2 = findViewById(R.id.view_pager2);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        User user1 = DataLocalManager.getUser();
        if (user1 == null){
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser u = mAuth.getCurrentUser();
            if (u != null){
                mAuth.signOut();
            }
        }

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            Toast.makeText(this, "Dã đăng nhập", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Chưa đăng nhập", Toast.LENGTH_SHORT).show();
        }

        MainViewpager2Adapter adapter = new MainViewpager2Adapter(this);
        mViewPager2.setAdapter(adapter);
        mViewPager2.setPageTransformer(new DepthPageTransformer());
        mViewPager2.setUserInputEnabled(false);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_category).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_cart).setChecked(true);
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_promotion).setChecked(true);
                        break;
                    case 4:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_account).setChecked(true);
                        break;
                }
            }
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.menu_home){
                    mViewPager2.setCurrentItem(0);
                }else if (item.getItemId()==R.id.menu_category){
                    mViewPager2.setCurrentItem(1);
                }else if (item.getItemId()==R.id.menu_cart){
                    mViewPager2.setCurrentItem(2);
                }else if (item.getItemId()==R.id.menu_promotion){
                    mViewPager2.setCurrentItem(3);
                }else{
                    mViewPager2.setCurrentItem(4);
                }

                return true;
            }
        });

        User user = DataLocalManager.getUser();
        if (user != null){

        }else{

        }
    }

    public void chooseCategoryFragment(){
        mBottomNavigationView.getMenu().findItem(R.id.menu_category).setChecked(true);
        mViewPager2.setCurrentItem(1);
    }

}