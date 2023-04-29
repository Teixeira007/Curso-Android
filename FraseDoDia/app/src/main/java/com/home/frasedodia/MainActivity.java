package com.home.frasedodia;

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

    public void gerarFrasedoDia(View view){

        String[] frases = {
                "Comece onde você está, use o que você tem e faça o que você pode.",
                "Tudo o que um sonho precisa para ser realizado é alguém que acredite que ele possa ser realizado.",
                "Devíamos ser ensinados a não esperar por inspiração para começar algo. Ação sempre gera inspiração. Inspiração raramente gera ação.",
                "Não importa que você vá devagar, contanto que você não pare.",
                "A inspiração existe, porém temos que encontrá-la trabalhando.",
                "Coragem é saber o que não temer.",
                "Conhecer a si mesmo é o começo de toda sabedoria.",
                "Descubra quem é você, e seja essa pessoa. A sua alma foi colocada nesse mundo para ser isso, então viva essa verdade e todo resto virá.",
                "Não existe nada de completamente errado no mundo, mesmo um relógio parado, consegue estar certo duas vezes por dia.",
                "Não é a carga que o derruba, mas a maneira como você a carrega.",
                "A vida é 10% o que acontece a você e 90% como você reage a isso."
        };

        Random gerador = new Random();

        int indexFrase = gerador.nextInt(11);

        TextView text = findViewById(R.id.result);
        text.setText(frases[indexFrase]);

    }
}