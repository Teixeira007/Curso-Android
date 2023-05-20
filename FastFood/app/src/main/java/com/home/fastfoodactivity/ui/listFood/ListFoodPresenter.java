package com.home.fastfoodactivity.ui.listFood;

import com.home.fastfoodactivity.data.dto.DtoFood;
import com.home.fastfoodactivity.data.model.Food;
import com.home.fastfoodactivity.data.network.ApiFood;
import com.home.fastfoodactivity.data.network.response.FoodResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFoodPresenter implements ListFoodContract.presenter{

    private ListFoodContract.view view;

    public ListFoodPresenter(ListFoodContract.view view) {
        this.view = view;
    }

    @Override
    public void getFoods() {
        ApiFood.getINSTANCE().getBestFoods()
                .enqueue(new Callback<List<FoodResponse>>() {
                    @Override
                    public void onResponse(Call<List<FoodResponse>> call, Response<List<FoodResponse>> response) {
                        if(response.isSuccessful()){
                            List<Food> foods = DtoFood.convertFoodResponseForFood(response.body());
                            view.showFoods(foods);
                        }else{
                            view.showMessageError();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<FoodResponse>> call, Throwable t) {
                        view.showMessageError();
                    }
                });
    }

    @Override
    public void destroyView() {
        this.view = null;
    }
}
