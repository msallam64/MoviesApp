package com.example.ecom_app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecom_app.R;
import com.example.ecom_app.models.home.usermodel.UserLog;


public class ProfileFragment extends Fragment {
    UserLog userLog;
    private static final String IMAGE_URL = "https://e-commerce-dev.intcore.net/";


    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(UserLog param1) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userLog = (UserLog) getArguments().getSerializable("user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageView imageView;
        TextView username,usermail;
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imageView=view.findViewById(R.id.profile_image);
        username=view.findViewById(R.id.tv_profile_name);
        usermail=view.findViewById(R.id.tv_profile_mail);

        if (userLog != null) {
            String imgUrl = userLog.getImage();
            Glide.with(getContext()).load(IMAGE_URL + imgUrl)
                    .into(imageView);
            username.setText(userLog.getName());
            usermail.setText(userLog.getEmail());

        }

        return view;
    }

}
