package com.example.ecom_app.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecom_app.R;
import com.example.ecom_app.models.home.home.BestSeller;

import java.util.List;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.BestHolder> {
    List<BestSeller> list;
    Context context;
    private static final String IMAGE_URL = "https://e-commerce-dev.intcore.net/";

    public BestSellerAdapter(List<BestSeller> moviesList) {
        this.list = moviesList;
    }

    @NonNull
    @Override
    public BestHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.new_arrival_listitem, viewGroup, false);
        return new BestHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BestHolder myHolder, int i) {
        BestSeller dataModel = list.get(i);
        myHolder.New_Name.setText(dataModel.getNameEn());
        int price = dataModel.getPrice();
        String x = String.valueOf(price);
        String text = String.format(context.getString(R.string.price), x);

        myHolder.Price.setText(text);
        String imgUrl = dataModel.getDefaultImage();

        Glide.with(context).load(IMAGE_URL + imgUrl)
                .into(myHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BestHolder extends RecyclerView.ViewHolder {
        TextView New_Name, Price;
        ImageView imageView;

        public BestHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            New_Name = itemView.findViewById(R.id.new_arrival_name);
            Price = itemView.findViewById(R.id.new_arrival_price);
            imageView = itemView.findViewById(R.id.new_arrival_main_image);

        }
    }


}
