package com.example.apiuserdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.apiuserdata.adapter.UserListAdapter;
import com.example.apiuserdata.model.UserListResponseModel;
import com.example.apiuserdata.retrofit.APIcall;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private static String BASE_URL = "https://api.github.com/";

    RecyclerView recyclerView;
    List<UserListResponseModel> userListResponseModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvUsersList);
        userListResponseModelList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIcall apIcall = retrofit.create(APIcall.class);

        Call<List<UserListResponseModel>> userList = apIcall.getUserData();

        userList.enqueue(new Callback<List<UserListResponseModel>>() {
            @Override
            public void onResponse(Call<List<UserListResponseModel>> call, Response<List<UserListResponseModel>> response) {
                if (response.code() != 200) {
                    return;
                }
                List<UserListResponseModel> users = response.body();

                for (UserListResponseModel userListResponseModel : users) {
                    userListResponseModelList.add(userListResponseModel);
                    //Log.v("Response",""+userListResponseModel.getName());
                }
                FetchData(userListResponseModelList);
            }

            @Override
            public void onFailure(Call<List<UserListResponseModel>> call, Throwable t) {

            }
        });

    }

    private void FetchData(List<UserListResponseModel> userListResponseModelList) {
        UserListAdapter userListAdapter = new UserListAdapter(this, userListResponseModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userListAdapter);
    }
}