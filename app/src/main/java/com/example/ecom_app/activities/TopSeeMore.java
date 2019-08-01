package com.example.ecom_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.ecom_app.R;
import com.example.ecom_app.adapters.TopAdapter;
import com.example.ecom_app.models.home.home.Home;
import com.example.ecom_app.models.home.home.TopCategory;
import com.example.ecom_app.networking.MyRequests;
import com.example.ecom_app.networking.MyRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopSeeMore extends AppCompatActivity {
    RecyclerView recyclerView;
    TopAdapter topAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_see_more);
        recyclerView = findViewById(R.id.rv_seemore_top);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyRequests dataInterface = MyRetrofit.getRetrofitInstance().create(MyRequests.class);
        Call<Home> dataModelCall = dataInterface.getData();
        dataModelCall.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                Home home = response.body();
                List<TopCategory> categories = home.getTopCategories();
                categories.add(new TopCategory(1));
                topAdapter = new TopAdapter(categories, getApplicationContext(), new TopAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                    }
                });
                recyclerView.setAdapter(topAdapter);
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {

            }
        });

    }
}
