package com.example.ecom_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecom_app.R;
import com.example.ecom_app.models.home.topcategorymodels.Data;

import java.util.Date;
import java.util.List;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.myHolder> {
    List<Data> dateList;
    Context context;
    private static final String IMAGE_URL = "https://e-commerce-dev.intcore.net/";
    public CategoryItemAdapter(List<Data> moviesList) {
        this.dateList = moviesList;
    }
    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_listitem, viewGroup, false);
        return new myHolder(itemView);
    }
    @Override
    public int getItemCount() {
        return dateList.size();
    }
    @Override
    public void onBindViewHolder(@NonNull myHolder myHolder, int i) {
        Data dataModel = dateList.get(i);
        myHolder.New_Name.setText(dataModel.getNameEn());
        int price = dataModel.getPrice();
        String x = String.valueOf(price);
        String text = String.format(context.getString(R.string.price), x);
        myHolder.Price.setText(text);
        String imgUrl = dataModel.getDefaultImage();
        Glide.with(context).load(IMAGE_URL + imgUrl)
                .into(myHolder.imageView);


    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView New_Name, Price;
        ImageView imageView;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            New_Name = itemView.findViewById(R.id.category_name);
            Price = itemView.findViewById(R.id.gategory_price);
            imageView = itemView.findViewById(R.id.iv_category);

        }
    }


}
