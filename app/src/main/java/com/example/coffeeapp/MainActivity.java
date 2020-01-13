package com.example.coffeeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    public static API_INTERFACE api_interface;
    String[] coffeeType = {"Cappucino","Americano","Espresso","Latte","Mocha","Macchiato"};
    String preferedCoffee = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api_interface = API_CLIENT.getApiClient().create(API_INTERFACE.class);

        TextView dropdown = findViewById(R.id.drop_down);
        ViewCompat.setTranslationZ(dropdown, 5);

        Spinner coffeeDropdown = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,coffeeType);
        coffeeDropdown.setAdapter(adapter);

        Spinner coffeeSelected = (Spinner) findViewById(R.id.spinner);
        coffeeSelected.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                preferedCoffee = coffeeType[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Button adminBtn = findViewById(R.id.adminBtn);
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adminPage = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(adminPage);
            }
        });
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing order...");
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait");
        progressDialog.setIndeterminate(false);
        progressDialog.show();

        RadioButton hot = findViewById(R.id.hotCoffee);
        Boolean hotCoffee =  hot.isChecked();
        RadioButton cold = findViewById(R.id.coldCoffee);
        Boolean coldCoffee = cold.isChecked();

        EditText nameText = findViewById(R.id.name_text);
        String username = nameText.getText().toString();
        String messsage = createOrderSummary(hotCoffee, coldCoffee, username);
        TextView mail = findViewById(R.id.mail_text);
        String email = mail.getText().toString();
        TextView add = findViewById(R.id.address);
        String address = add.getText().toString();

        Order order = new Order();
        order.setClient_name(username);
        order.setHot_or_cold(hotOrCold);
        order.setCoffee_type(preferedCoffee);
        order.setCost(calculatePrice(quantity,hotCoffee,coldCoffee));
        order.setQuantity_ordered(quantity);
        order.setClient_address(address);
        order.setClient_email(email);
        Call<Order> orderCall = api_interface.orderingCoffee(order);

        orderCall.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.body().getResponse().equals("true")){
                    String newMessage = "****************************\nName: "+ response.body().getClient_name()+"\n";
                    newMessage += "****************************\n"+"Coffee type: "+response.body().getCoffee_type();
                    newMessage += "\n****************************\n"+"Hot or Cold: "+response.body().getHot_or_cold()+ "\n";
                    newMessage+= "****************************\nQuantity: "+ response.body().getQuantity_ordered()+"\n";
                    newMessage+="****************************\nTotal: Ghc"+ response.body().getCost();
                    newMessage += "\n****************************\nThank You!\n****************************";
                    progressDialog.dismiss();

                    Intent detailsPage = new Intent(getApplicationContext(), orderSummary.class);
                    detailsPage.putExtra("summaryOfOrder",newMessage);
                    startActivity(detailsPage);
                    Toast.makeText(getApplicationContext(),response.body().getResponse(),Toast.LENGTH_LONG).show();
                }else if(response.body().getResponse().equals("false")){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"False response",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Processing order...");
//        progressDialog.setCancelable(false);
//        progressDialog.setTitle("Please wait");
//        progressDialog.setIndeterminate(false);
//        progressDialog.show();

//       Intent detailsPage = new Intent(this, orderSummary.class);
//        detailsPage.putExtra("summaryOfOrder",messsage);
//        startActivity(detailsPage);
//        displayMessage(messsage);

//        EditText sendTo = findViewById(R.id.mail_text);
//        String mailTo = sendTo.getText().toString();

//        final String company = "nkansahisaac41@gmail.com";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth","true");
//        props.put("mail.smtp.starttls.enable","true");
//        props.put("mail.smtp.host","smtp.gmail.com");
//        props.put("mail.smtp.port","587");

//        Intent mail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","nkansahisaac41@gmail",null));
//        mail.putExtra(mail.EXTRA_EMAIL, mailTo);
//        mail.putExtra(mail.EXTRA_SUBJECT,"Just java coffee order for " + username);
//        mail.putExtra(mail.EXTRA_TEXT, createOrderSummary(wantChocolate,hasWhippedCream,username));

//        if (mail.resolveActivity(getPackageManager()) != null){
//            startActivity(mail);
//        }
    }

    String hotOrCold = "";
    private String createOrderSummary(boolean hot, boolean cold, String name){

        if (hot == true){ hotOrCold = "Hot Coffee"; }else{ hotOrCold = "Cold Coffee"; }

        String newMessage = "****************************\nName: "+ name+"\n";
        newMessage += "****************************\n"+"Coffee type: "+preferedCoffee;
        newMessage += "\n****************************\n"+"Hot or Cold: "+hotOrCold+ "\n";
        newMessage+= "****************************\nQuantity: "+ quantity+"\n";
        newMessage+="****************************\nTotal: Ghc"+ calculatePrice(quantity, hot, cold);
        newMessage += "\n****************************\nThank You!\n****************************";
        return newMessage;
    }

    private int calculatePrice(int quantity, boolean hotCoffee, boolean coldCoffee){
        int coffeePrice = 5;
        int hotCoffeePrice = 0;
        int coldCoffeePrice = 0;

        if(hotCoffee == true){ hotCoffeePrice = 2; }
        if(coldCoffee == true){ coldCoffeePrice = 1; }

        int basePrice = coffeePrice + hotCoffeePrice + coldCoffeePrice;
        int total = basePrice * quantity;
        return total;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

//    private void displayMessage(String message){
//        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    int quantity = 2;
    public void increment(View view){
        quantity += 1;
        if (quantity >= 100){
            Toast.makeText(this, "Coffees can't exceed 100", Toast.LENGTH_SHORT).show();
            quantity = 100;
        }
        display(quantity);
    }

    public void decrement(View view){
        quantity -= 1;
        if (quantity <= 1){
            Toast.makeText(this, "Coffees can't be less than 1", Toast.LENGTH_SHORT).show();
            quantity = 1;
        }
        display(quantity);
    }
}