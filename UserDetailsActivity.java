package com.example.apiuserdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class UserDetailsActivity extends AppCompatActivity {

    ImageView image;
    TextView name;

    String uImage,uName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        image = findViewById(R.id.duser_image);
        name =  findViewById(R.id.duser_name);


        uImage = getIntent().getStringExtra("useravatar");
        uName = getIntent().getStringExtra("username");

        Glide.with(this).load(uImage).into(image);
        name.setText(uName);

    }
}

