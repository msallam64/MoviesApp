package com.example.ecom_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ecom_app.R;
import com.example.ecom_app.models.home.topcategorymodels.Data;
import com.example.ecom_app.models.home.topcategorymodels.ProductsModel;
import com.example.ecom_app.networking.MyRequests;
import com.example.ecom_app.networking.MyRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopCategoryItem extends AppCompatActivity {
    Intent intent;
    int id;
    String categoryimage;
    private static final String IMAGE_URL = "https://e-commerce-dev.intcore.net/";
    ImageView topimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_category_item);
        intent = getIntent();
        categoryimage = intent.getStringExtra("topimage");
        id = intent.getIntExtra("id", 0);
        topimage=findViewById(R.id.iv_category);
        Glide.with(getApplicationContext()).load(IMAGE_URL + categoryimage)
                .into(topimage);
        String idcategory=String.valueOf(id);
        MyRequests dataInterface = MyRetrofit.getRetrofitInstance().create(MyRequests.class);
        Call<ProductsModel> dataModelCall = dataInterface.getcategory(idcategory);
        dataModelCall.enqueue(new Callback<ProductsModel>() {
            @Override
            public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {
                ProductsModel productsModel=response.body();
                List<Data> data=productsModel.getData();

            }

            @Override
            public void onFailure(Call<ProductsModel> call, Throwable t) {

            }
        });


    }
}
