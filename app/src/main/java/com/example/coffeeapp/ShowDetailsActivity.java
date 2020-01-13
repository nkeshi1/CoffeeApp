package com.example.coffeeapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDetailsActivity extends AppCompatActivity {
    List<GetOrders> orders;
    public static API_INTERFACE api_interface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        final int position = getIntent().getIntExtra("userSelected",0);
        api_interface = API_CLIENT.getApiClient().create(API_INTERFACE.class);
        final TextView id = new TextView(getApplicationContext());
        Call<GetOrders> getOrdersCall = api_interface.getOrders();
        getOrdersCall.enqueue(new Callback<GetOrders>() {
            @Override
            public void onResponse(Call<GetOrders> call, Response<GetOrders> response) {
                    orders = response.body().getGetorders();
                    TextView mail = findViewById(R.id.detail_mail);
                    TextView address = findViewById(R.id.details_address);
                    TextView coffeeType = findViewById(R.id.details_coffeeType);
                    TextView date = findViewById(R.id.details_date);
                    TextView hotOrCold = findViewById(R.id.details_hotCold);
                    TextView name = findViewById(R.id.details_name);
                    TextView isServed = findViewById(R.id.details_isServed);
                    TextView quantity = findViewById(R.id.details_qty);
                    TextView cost = findViewById(R.id.details_cost);
                    id.setText(""+orders.get(position).getClient_id());
                    cost.setText("Ghc: "+orders.get(position).getCost());
                    coffeeType.setText(""+orders.get(position).getCoffee_type());
                    date.setText(""+orders.get(position).getDateOrdered());
                    hotOrCold.setText(""+orders.get(position).getHot_or_cold());
                    name.setText(""+orders.get(position).getClient_name());
                    isServed.setText(""+orders.get(position).getIs_served());
                    quantity.setText(""+orders.get(position).getQuantity_ordered());
                    mail.setText(""+orders.get(position).getClient_mail());
                    address.setText(""+orders.get(position).getClient_address());
            }

            @Override
            public void onFailure(Call<GetOrders> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_LONG).show();
            }
        });

        Button serveBtn = findViewById(R.id.serveCustomerBtn);
        serveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
                progressDialog.setMessage("Sending order to agent Desmond");
                progressDialog.setCancelable(false);
                progressDialog.setTitle("Please wait");
                progressDialog.setIndeterminate(false);
                progressDialog.show();*/

                TextView isServed = findViewById(R.id.details_isServed);
                if(isServed.getText().equals("true")){
                    Toast.makeText(getApplicationContext(),"Customer is already served!", Toast.LENGTH_LONG).show();
                }else{
                    Order updateServe = new Order();
                    updateServe.setClient_id(Integer.parseInt(id.getText().toString()));
                    updateServe.setIsServed("true");

                    Call<Order> updateStatus = api_interface.updatingStatus(updateServe);
                    updateStatus.enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {
                            Toast.makeText(getApplicationContext(), "Status Updated: ", Toast.LENGTH_LONG).show();

//                            if(response.body().getResponse().equals("true")){
//
//                            }else{
//
//                            }
                        }

                        @Override
                        public void onFailure(Call<Order> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
