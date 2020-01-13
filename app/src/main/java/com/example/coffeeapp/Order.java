package com.example.coffeeapp;

public class Order {
    private String client_name, client_address, client_email, isServed, coffee_type, hot_or_cold, response;
    private int client_id ,quantity_ordered, cost;

    public String getResponse() { return response; }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setIsServed(String isServed) {
        this.isServed = isServed;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) { this.client_address = client_address; }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getCoffee_type() {
        return coffee_type;
    }

    public void setCoffee_type(String coffee_type) {
        this.coffee_type = coffee_type;
    }

    public String getHot_or_cold() {
        return hot_or_cold;
    }

    public void setHot_or_cold(String hot_or_cold) {
        this.hot_or_cold = hot_or_cold;
    }

    public int getQuantity_ordered() {
        return quantity_ordered;
    }

    public void setQuantity_ordered(int quantity_ordered) { this.quantity_ordered = quantity_ordered; }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
