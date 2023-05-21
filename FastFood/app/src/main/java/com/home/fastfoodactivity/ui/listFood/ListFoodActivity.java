package com.home.fastfoodactivity.ui.listFood;

import static com.home.fastfoodactivity.ui.detailsFood.DetailsFoodActivity.EXTRA_FOOD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Food;
import com.home.fastfoodactivity.data.model.ItemPedido;
import com.home.fastfoodactivity.ui.detailsFood.DetailsFoodActivity;
import com.home.fastfoodactivity.ui.listCart.ListCartAdapter;

import java.util.List;

public class ListFoodActivity extends AppCompatActivity implements ListFoodContract.view, ListFoodAdapter.ClickItem {

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewCart;
    private ListFoodAdapter adapter;
    private ListFoodPresenter presenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configToolBar();
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


    //Configurações da ToolBar

    public void configToolBar(){
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category_tool_bar, menu);
        getMenuInflater().inflate(R.menu.menu_cart_tool_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.menu_cart_item){
            showPopupCart(item.getActionView());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showPopupCart(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_cart, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Toast.makeText(this, "Tá funcionando", Toast.LENGTH_LONG).show();

        recyclerViewCart = dialogView.findViewById(R.id.my_recycler_view_cart);

        configAdapterCart(recyclerViewCart);

    }

    public void configAdapterCart(RecyclerView recyclerViewCart){
        ListCartAdapter listCartAdapter = new ListCartAdapter();
//        DetailsFoodActivity detailsFoodActivity = new DetailsFoodActivity(listCartAdapter);

        listCartAdapter.setListCart(
                new ItemPedido(
                new Food("Pão",
                "https://goldbelly.imgix.net/uploads/showcase_media_asset/image/79619/joes-kc-ribs-brisket-and-burnt-ends.6710e994980e485e6441b794717ad6fb.jpg?ixlib=react-9.0.2&auto=format&ar=1%3A1",
                34, "Pão delicia", 5), 5));

        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(this);

        recyclerViewCart.setLayoutManager(linearLayout);
        recyclerViewCart.setAdapter(listCartAdapter);
    }
}