package com.example.coffeeapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {
    String tabTitle;
    List<GetOrders> orders;
    public static API_INTERFACE api_interface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tabTitle = "all orders";
        api_interface = API_CLIENT.getApiClient().create(API_INTERFACE.class);
          allOrders();
        TabLayout all = findViewById(R.id.tabLayout);
        all.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabTitle = tab.getText().toString();
                allOrders();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void allOrders(){
        final GetOrders getOrders = new GetOrders();
//        final TextView id = findViewById(R.id.client_id);
        Call<GetOrders> getOrdersCall = api_interface.getOrders();

        getOrdersCall.enqueue(new Callback<GetOrders>() {
            @Override
            public void onResponse(Call<GetOrders> call, Response<GetOrders> response) {

                orders=response.body().getGetorders();
                final ListView nameList = findViewById(R.id.namesList);
                final adapterclass adapter = new adapterclass(getApplicationContext(),orders, tabTitle);
                nameList.setAdapter(adapter);

                nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent details = new Intent(AdminActivity.this, ShowDetailsActivity.class);
                        details.putExtra("userSelected",i);
                        startActivity(details);
                    }
                });
            }

            @Override
            public void onFailure(Call<GetOrders> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_LONG).show();
            }
        });
    }
}
