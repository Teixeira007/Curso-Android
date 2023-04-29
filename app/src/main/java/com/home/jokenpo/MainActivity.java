package com.home.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jokenPedra(View view){
        this.joken(Escolha.PEDRA);
        Escolha escolhaApp = escolhaApp();
    }

    public void jokenPapel(View view){
        this.joken(Escolha.PAPEL);
        Escolha escolhaApp = escolhaApp();
    }

    public void jokenTesoura(View view){
        this.joken(Escolha.TESOURA);
        Escolha escolhaApp = escolhaApp();
    }

    public void joken(Escolha escolha){
        Escolha escolhaApp = escolhaApp();


        Escolha escolhaUsuario = escolha;

        TextView result = findViewById(R.id.result);
        ImageView image = findViewById(R.id.imageResult);


        if(escolhaApp == Escolha.TESOURA){
            image.setImageResource(R.drawable.tesoura);
            if(escolhaUsuario == Escolha.TESOURA){
                result.setText("Empatou!");
            }else if(escolhaUsuario == Escolha.PAPEL){
                result.setText("Você perdeu!");
            }else{
                result.setText("Você ganhou!");
            }
        }
        else if(escolhaApp == Escolha.PEDRA) {
            image.setImageResource(R.drawable.pedra);
            if(escolhaUsuario == Escolha.PEDRA){
                result.setText("Empatou!");
            }else if(escolhaUsuario == Escolha.TESOURA){
                result.setText("Você perdeu!");
            }else{
                result.setText("Você ganhou!");
            }
        }else{
            image.setImageResource(R.drawable.papel);
            if(escolhaUsuario == Escolha.PAPEL){
                result.setText("Empatou!");
            }else if(escolhaUsuario == Escolha.TESOURA){
                result.setText("Você perdeu!");
            }else{
                result.setText("Você ganhou!");
            }
        }
    }

    public Escolha escolhaApp(){
        Random gerador = new Random();
        Escolha[] escolhaJoken = {Escolha.PAPEL, Escolha.PEDRA, Escolha.TESOURA};

        int indexEscolhaApp = gerador.nextInt(3);
        Escolha escolha = escolhaJoken[indexEscolhaApp];
        return escolha;
    }


}