package com.home.retrofitgithub;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.home.retrofitgithub.network.GitHubClient;
import com.home.retrofitgithub.network.GitResp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configAdapter();
    }


    public  void configAdapter(){
        recyclerView = findViewById(R.id.my_recycler_view);

        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayout);

        adapter = new Adapter();

        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubClient gitHubClient = retrofit.create(GitHubClient.class);

        gitHubClient.getListGitRepositories("teixeira007")
                .enqueue(new Callback<List<GitResp>>() {
                    @Override
                    public void onResponse(Call<List<GitResp>> call, Response<List<GitResp>> response) {
                        List<GitResp> listGitRes = response.body();
                        adapter.setRepositories(listGitRes);
                    }

                    @Override
                    public void onFailure(Call<List<GitResp>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "error", LENGTH_SHORT).show();
                    }
                });
    }
}