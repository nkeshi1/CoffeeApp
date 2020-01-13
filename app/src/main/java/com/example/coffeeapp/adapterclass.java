package com.example.coffeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class adapterclass extends ArrayAdapter<GetOrders> {
    String tabTitle = "";
    private TextView id, name, address, served, date, cost, quantity, mail, coffeeType;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GetOrders orders = getItem(position);
        String check = null;

        if(tabTitle.equals("served orders")){
            check="true";
        }else if(tabTitle.equals("unserved orders")){
            check="false";
        }else if(tabTitle.equals("all orders")){
            check="all";
        }

        if(check.equals("all")){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.all_orders_page, parent, false);

            id = new TextView(getContext());
            id.setText(""+orders.getClient_id());
            name = convertView.findViewById(R.id.name);
            address = convertView.findViewById(R.id.address);
            served = convertView.findViewById(R.id.served);
            date = convertView.findViewById(R.id.A_dateTime);
            cost = convertView.findViewById(R.id.A_cost);
            quantity = convertView.findViewById(R.id.A_quantity_ordered);
            mail = convertView.findViewById(R.id.A_client_mail);
            TextView coffeeType = convertView.findViewById(R.id.A_coffee_type);
            coffeeType.setText(orders.getCoffee_type());
            coffeeType.setVisibility(View.INVISIBLE);
            TextView hotOrCold = convertView.findViewById(R.id.A_hotOrCold);
            hotOrCold.setText(orders.getHot_or_cold());
            hotOrCold.setVisibility(View.INVISIBLE);
            mail.setText(orders.getClient_mail());
            mail.setVisibility(View.INVISIBLE);
            date.setText(orders.getDateOrdered());
            date.setVisibility(View.INVISIBLE);
            name.setText(orders.getClient_name());
            quantity.setText(""+orders.getQuantity_ordered());
            quantity.setVisibility(View.INVISIBLE);
            cost.setText(""+orders.getCost());
            cost.setVisibility(View.INVISIBLE);
            address.setText(orders.getClient_address());
            served.setText(orders.getIs_served());
            Toast.makeText(getContext(),name.getText().toString(),Toast.LENGTH_LONG).show();
            Toast.makeText(getContext(),served.getText().toString(),Toast.LENGTH_LONG).show();
            Toast.makeText(getContext(),address.getText().toString(),Toast.LENGTH_LONG).show();
        }
        else {
            if (orders.getIs_served().equals(check)) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.all_orders_page, parent, false);

                name = convertView.findViewById(R.id.name);
                address = convertView.findViewById(R.id.address);
                served = convertView.findViewById(R.id.served);
                date = convertView.findViewById(R.id.A_dateTime);
                cost = convertView.findViewById(R.id.A_cost);
                quantity = convertView.findViewById(R.id.A_quantity_ordered);
                mail = convertView.findViewById(R.id.A_client_mail);
                coffeeType = convertView.findViewById(R.id.A_coffee_type);
                coffeeType.setText(orders.getCoffee_type());
                coffeeType.setVisibility(View.INVISIBLE);
                TextView hotOrCold = convertView.findViewById(R.id.A_hotOrCold);
                hotOrCold.setText(orders.getHot_or_cold());
                hotOrCold.setVisibility(View.INVISIBLE);
                mail.setText(orders.getClient_mail());
                mail.setVisibility(View.INVISIBLE);
                date.setText(orders.getDateOrdered());
                date.setVisibility(View.INVISIBLE);
                name.setText(orders.getClient_name());
                quantity.setText(""+orders.getQuantity_ordered());
                quantity.setVisibility(View.INVISIBLE);
                cost.setText(""+orders.getCost());
                cost.setVisibility(View.INVISIBLE);
                address.setText(orders.getClient_address());
                served.setText(orders.getIs_served());
            } else  {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.blan_item, parent, false);
            }
        }
        return convertView;
    }

    public adapterclass(Context context, List<GetOrders> items, String tabtitle) {
        super(context, 0,items);
        this.tabTitle = tabtitle;
    }
}
