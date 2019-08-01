package com.example.ecom_app.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecom_app.R;
import com.example.ecom_app.models.home.home.SideMenuCategory;

import java.util.List;

public class SideMenuAdapter extends RecyclerView.Adapter<SideMenuAdapter.SideHolder> {
    List<SideMenuCategory> sideMenuCategories;

    public SideMenuAdapter(List<SideMenuCategory> moviesList) {
        this.sideMenuCategories = moviesList;
    }

    @NonNull
    @Override
    public SideHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.side_menu_item, viewGroup, false);
        return new SideHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SideHolder myHolder, int i) {
        SideMenuCategory dataModel = sideMenuCategories.get(i);
        myHolder.catagory_Name.setText(dataModel.getNameEn());

    }

    @Override
    public int getItemCount() {
        return sideMenuCategories.size();
    }

    public class SideHolder extends RecyclerView.ViewHolder {
        TextView catagory_Name;

        public SideHolder(@NonNull View itemView) {
            super(itemView);
            catagory_Name = itemView.findViewById(R.id.tv_side_menu_item);

        }
    }
}
