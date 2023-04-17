package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void random(View view){
        Random gerador = new Random();

        int number = gerador.nextInt(51);
        TextView result = findViewById(R.id.result);

        result.setText("O número sorteado é "+number);
    }


}
