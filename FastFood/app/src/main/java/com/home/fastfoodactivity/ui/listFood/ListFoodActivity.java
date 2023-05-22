package com.home.fastfoodactivity.ui.listFood;

import static com.home.fastfoodactivity.ui.detailsFood.DetailsFoodActivity.EXTRA_FOOD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.home.fastfoodactivity.R;
import com.home.fastfoodactivity.data.model.Food;
import com.home.fastfoodactivity.data.model.ItemPedido;
import com.home.fastfoodactivity.data.model.Pedido;
import com.home.fastfoodactivity.data.room.AppDatabase;
import com.home.fastfoodactivity.ui.detailsFood.DetailsFoodActivity;
import com.home.fastfoodactivity.ui.listCart.CartData;
import com.home.fastfoodactivity.ui.listCart.ListCartAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListFoodActivity extends AppCompatActivity implements ListFoodContract.view, ListFoodAdapter.ClickItem {

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewCart;
    private ListFoodAdapter adapter;
    private ListFoodPresenter presenter;
    private ListCartAdapter listCartAdapter;
    private TextView total;
    private Toolbar toolbar;

    private Button finalizarPedido;
    private AppDatabase db;


    public static String EXTRA_CART = "EXTRA_CART";

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

    //Configurando Cart

    @SuppressLint("MissingInflatedId")
    public void showPopupCart(View view){

        //criar o dialog view
        View dialogView = createDialogView();

        recyclerViewCart = dialogView.findViewById(R.id.my_recycler_view_cart);
        total = dialogView.findViewById(R.id.total_cart);

        //calcula valor total do pedido
        double totalValue = calculateTotal();
        total.setText("Total: $"+Double.toString(totalValue));

        //Butão finalizar Pedido
        finalizarPedido = dialogView.findViewById(R.id.finalizar_pedido);


        //configurar o adapter
        configAdapterCart(recyclerViewCart);

        configFinalizarPedido();

    }

    public View createDialogView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_cart, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

        return dialogView;
    }

    public void configAdapterCart(RecyclerView recyclerViewCart){
        listCartAdapter = new ListCartAdapter();

        ItemPedido itemPedido = (ItemPedido) getIntent().getSerializableExtra(EXTRA_CART);

        listCartAdapter.setListCart(CartData.getCartItems());

        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(this);

        recyclerViewCart.setLayoutManager(linearLayout);
        recyclerViewCart.setAdapter(listCartAdapter);
    }

    public double calculateTotal(){
        double total = 0;
        List<ItemPedido> itensCart = CartData.getCartItems();
        for(ItemPedido itens: itensCart){
            total += itens.getProduct().getPrice() * itens.getQuantity();
        }

        return total;
    }

    public void cleanCart(View view){
        CartData.cleanCart();
        listCartAdapter.setListCart(CartData.getCartItems());
        total.setText("Total: $0");
        Toast.makeText(this, "Carrinho de produtos limpo", Toast.LENGTH_SHORT).show();
    }

    //Finalizar Pedido

    public void configFinalizarPedido(){
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-database").build();

        finalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Pedido pedido = new Pedido(CartData.getCartItems(), calculateTotal());

                        db.pedidoDao().insertPedido(pedido);
                        CartData.cleanCart();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ListFoodActivity.this, "Pedido finalizado", Toast.LENGTH_SHORT).show();
                                listarPedidos();
                            }
                        });
                    }
                }).start();
            }
        });
    }

    public void listarPedidos(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Pedido> pedidos = db.pedidoDao().getAll();
                for(Pedido pedido: pedidos){
                    Log.i("Dados", "Total:"+pedido.getTotal());
                }
            }
        }).start();
    }
}