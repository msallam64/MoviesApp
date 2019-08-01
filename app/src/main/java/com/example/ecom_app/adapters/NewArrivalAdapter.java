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
import com.example.ecom_app.models.home.home.NewArrival;

import java.util.List;

public class NewArrivalAdapter extends RecyclerView.Adapter<NewArrivalAdapter.NewArrivalHolder> {
    List<NewArrival> list;
    Context context;
    private static final String IMAGE_URL = "https://e-commerce-dev.intcore.net/";


    public NewArrivalAdapter(List<NewArrival> moviesList) {
        this.list = moviesList;
    }

    @NonNull
    @Override
    public NewArrivalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.new_arrival_listitem, viewGroup, false);
        return new NewArrivalHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewArrivalHolder myHolder, int i) {
        NewArrival dataModel = list.get(i);
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

    public class NewArrivalHolder extends RecyclerView.ViewHolder {
        TextView New_Name, Price;
        ImageView imageView;

        public NewArrivalHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            New_Name = itemView.findViewById(R.id.new_arrival_name);
            Price = itemView.findViewById(R.id.new_arrival_price);
            imageView = itemView.findViewById(R.id.new_arrival_main_image);

        }
    }


}
