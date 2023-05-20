package com.home.fastfoodactivity.ui.listFood;

import com.home.fastfoodactivity.data.model.Food;

import java.util.List;

public interface ListFoodContract {

    public interface view{

        void showFoods(List<Food> foods);

        void showMessageError();

    }

    public interface presenter{
        void getFoods();

        void destroyView();
    }
}
