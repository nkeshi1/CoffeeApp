package com.example.coffeeapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API_INTERFACE {
    @POST("newOrder.php")
    Call<Order> orderingCoffee(@Body Order userOrder);

    @POST("changeServeState.php")
    Call<Order> updatingStatus(@Body Order isServed);

    @GET("retrieveOrders.php")
    Call<GetOrders> getOrders();
}
