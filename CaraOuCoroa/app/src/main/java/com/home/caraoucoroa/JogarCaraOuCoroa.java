package com.home.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class JogarCaraOuCoroa extends AppCompatActivity {
    private static final String MOEDA = "MOEDA";
    private ImageView moeda;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogar_cara_ou_coroa);

        moeda = findViewById(R.id.imageMoeda);

        Random random = new Random();
        int randomNumber = random.nextInt(2);
        Log.d(MOEDA, "onCreate: "+randomNumber);
        if(randomNumber == 0){
            moeda.setImageResource(R.drawable.moeda_cara);
        }else if(randomNumber == 1){
            moeda.setImageResource(R.drawable.moeda_coroa);
        }
    }

    public void voltar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}