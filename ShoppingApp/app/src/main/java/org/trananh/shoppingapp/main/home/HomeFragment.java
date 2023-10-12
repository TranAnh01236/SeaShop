package org.trananh.shoppingapp.main.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.trananh.shoppingapp.R;
import org.trananh.shoppingapp.main.MainActivity;
import org.trananh.shoppingapp.main.SearchActivity;

public class HomeFragment extends Fragment {

    private View rootView;

    private MainActivity mMainActivity;
    private RelativeLayout mRelativeLayoutSearch;

    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mMainActivity = (MainActivity) getActivity();
        initialize();

        return rootView;
    }

    private void initialize(){

        Toolbar toolbar = rootView.findViewById(R.id.tool_bar);
        mMainActivity.setSupportActionBar(toolbar);
        mMainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        mRelativeLayoutSearch = rootView.findViewById(R.id.relativeLayout_search);

        mRelativeLayoutSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mMainActivity, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}