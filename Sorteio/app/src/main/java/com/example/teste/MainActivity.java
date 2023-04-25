package com.example.teste;

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

    public void randomNumber(View view){
        TextView text = findViewById(R.id.result);

        Random random = new Random();
        int number = random.nextInt(51);
        text.setText("O número sorteado é: "+number);
    }
}