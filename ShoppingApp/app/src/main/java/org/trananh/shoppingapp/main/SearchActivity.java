package org.trananh.shoppingapp.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.dhaval2404.imagepicker.ImagePicker;

import org.trananh.shoppingapp.R;

public class SearchActivity extends AppCompatActivity {

    private ImageButton btnBack, btnImageSearch;
    private EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initialize();
    }

    @Override
    public void finish() {
        super.finish();
    }

    private void initialize(){
        btnBack = findViewById(R.id.img_btn_back);
        txtSearch = findViewById(R.id.txt_search);
        btnImageSearch = findViewById(R.id.img_btn_image_search);

        txtSearch.requestFocus();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnImageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(SearchActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
    }
}