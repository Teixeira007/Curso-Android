package com.home.fastfoodactivity.ui.listFood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Food;

import java.util.List;

public class ListFoodActivity extends AppCompatActivity implements ListFoodContract.view{

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

        RecyclerView.LayoutManager gridLayout = new GridLayoutManager(this, 2);
        adapter = new ListFoodAdapter();


        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showFoods(List<Food> foods) {
        adapter.setFoods(foods);
    }

    @Override
    public void showMessageError() {
        Toast.makeText(this, "Erro ao carregar o cardápio", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroyView();
    }
}