package com.example.coffeeapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetOrders {
    @SerializedName("data")
    ArrayList<GetOrders> getorders;
    public String response, client_name, coffee_type, client_address, client_mail, hot_or_cold , is_served, dateOrdered, dateServed;
    private int cost, client_id, quantity_ordered;

    public List<GetOrders> getGetorders() {
        return getorders;
    }

    public int getCost() { return cost; }

    public int getQuantity_ordered() {return quantity_ordered;}

    public int getClient_id() { return client_id; }

    public String getDateOrdered(){return dateOrdered;}

    public String getClient_mail() { return client_mail; }

    public void setGetorders(ArrayList<GetOrders> getorders) {
        this.getorders = getorders;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getCoffee_type() {
        return coffee_type;
    }

    public void setCoffee_type(String coffee_type) {
        this.coffee_type = coffee_type;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public String getHot_or_cold() {
        return hot_or_cold;
    }

    public void setHot_or_cold(String hot_or_cold) {
        this.hot_or_cold = hot_or_cold;
    }

    public String getIs_served() {
        return is_served;
    }

    public void setIs_served(String is_served) {
        this.is_served = is_served;
    }
}
