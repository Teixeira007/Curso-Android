package com.home.fastfoodactivity.data.network;

import com.home.fastfoodactivity.data.network.response.FoodResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodService {

    @GET("best-foods")
    Call<List<FoodResponse>> getBestFoods();
}
