package com.example.ecom_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ecom_app.R;

public class ActivityActivation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
        Intent intent=getIntent();
        String activation_code=intent.getStringExtra("code");
        Toast.makeText(this,"Code sent ",Toast.LENGTH_LONG).show();

    }
}
