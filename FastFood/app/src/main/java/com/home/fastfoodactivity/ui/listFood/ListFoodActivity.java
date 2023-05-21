package com.home.fastfoodactivity.ui.listFood;

import static com.home.fastfoodactivity.ui.detailsFood.DetailsFoodActivity.EXTRA_FOOD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Food;
import com.home.fastfoodactivity.ui.detailsFood.DetailsFoodActivity;

import java.util.List;

public class ListFoodActivity extends AppCompatActivity implements ListFoodContract.view, ListFoodAdapter.ClickItem {

    private RecyclerView recyclerView;
    private ListFoodAdapter adapter;
    private ListFoodPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configAdapter();

        presenter = new ListFoodPresenter(this);
        presenter.getFoods();
    }

    public void configAdapter(){
        recyclerView = findViewById(R.id.my_recycler_view);

        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(this);
        adapter = new ListFoodAdapter(this);


        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showFoods(List<Food> foods) {
        adapter.setFoods(foods);
    }

    @Override
    public void showMessageError() {
        Toast.makeText(this, "Erro ao carregar o cardápio", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroyView();
    }

    @Override
    public void onItemClicked(Food food) {
        Intent intent = new Intent(this, DetailsFoodActivity.class);
        intent.putExtra(EXTRA_FOOD, food);
        startActivity(intent);
    }
}