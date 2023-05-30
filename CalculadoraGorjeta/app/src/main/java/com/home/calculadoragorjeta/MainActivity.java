package com.home.calculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarPorcentagem;
    private EditText valorGorjeta;
    private TextView porcentagemGorjeta;
    private EditText textporcentagemGorjeta;
    private EditText editTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcularGorjeta();
    }

    public void calcularGorjeta(){
        seekBarPorcentagem = findViewById(R.id.seekBarGorjeta);
        porcentagemGorjeta = findViewById(R.id.porcentagemGorjeta);
        textporcentagemGorjeta = findViewById(R.id.TextporcentagemGorjeta);
        valorGorjeta = findViewById(R.id.valorGorjeta);
        editTotal = findViewById(R.id.editTotal);


        seekBarPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagemGorjeta.setText(i+"%");
                textporcentagemGorjeta.setText(i+"%");

                if(valorGorjeta.getText()!=null && !valorGorjeta.getText().toString().isEmpty()){
                    Double valorGorjetaDouble = Double.parseDouble(valorGorjeta.getText().toString());
                    int porcentagem = seekBarPorcentagem.getProgress();
                    Double valorBonus = valorGorjetaDouble * (porcentagem/100.0) ;
                    Double valorTotalDouble = valorBonus + valorGorjetaDouble;

                    editTotal.setText("R$ "+valorTotalDouble);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}