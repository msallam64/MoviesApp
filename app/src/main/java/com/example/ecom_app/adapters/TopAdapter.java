package com.example.ecom_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecom_app.R;
import com.example.ecom_app.models.home.home.TopCategory;

import java.util.List;

public class TopAdapter extends RecyclerView.Adapter {

    private static RecyclerViewClickListener mListener;

    public interface RecyclerViewClickListener {

        void onClick(View view, int position);
    }

    private static final String IMAGE_URL = "https://e-commerce-dev.intcore.net/";


    List<TopCategory> topCategories;
    int type;
    Context context;

    public TopAdapter(List<TopCategory> data, Context context, RecyclerViewClickListener itemListener) {
        this.topCategories = data;
        this.context = context;
        this.mListener = itemListener;
        type = topCategories.size();
    }


    public static class SeeMoreItemHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public SeeMoreItemHolder(View itemView) {
            super(itemView);

            this.textView = itemView.findViewById(R.id.tv_top_category_listitem);
            this.imageView = itemView.findViewById(R.id.imageview_top_category_listitem);
        }


    }

    public static class TopCategoryHome extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;


        public TopCategoryHome(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.textView = itemView.findViewById(R.id.tv_top_category_listitem);
            this.imageView = itemView.findViewById(R.id.imageview_top_category_listitem);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());

        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TopCategory.HOME_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_category_home_listitem, parent, false);
                return new TopCategoryHome(view, mListener);
            case TopCategory.SEE_MORETYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.see_more_top_category_listitem, parent, false);
                return new SeeMoreItemHolder(view);

        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (TopCategory.type) {
            case 0:
                return TopCategory.HOME_TYPE;
            case 1:
                return TopCategory.SEE_MORETYPE;

            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {
        TopCategory object = topCategories.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case TopCategory.HOME_TYPE:
                    ((TopCategoryHome) holder).textView.setText(object.getNameEn());
                    String imgUrl = object.getImage();
                    Glide.with(context).load(IMAGE_URL + imgUrl)
                            .into(((TopCategoryHome) holder).imageView);
                    break;
                case TopCategory.SEE_MORETYPE:
                    ((SeeMoreItemHolder) holder).textView.setText(object.getNameEn());
                    String imgUrl2 = object.getImage();
                    Glide.with(context).load(IMAGE_URL + imgUrl2)
                            .into(((SeeMoreItemHolder) holder).imageView);


                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return topCategories.size();
    }

}
