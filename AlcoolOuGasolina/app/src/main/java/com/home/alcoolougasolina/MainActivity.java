
package com.home.alcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText alcool;
    private EditText gasolina;
    private TextView resultado;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alcool = findViewById(R.id.editAlcool);
        gasolina = findViewById(R.id.editGasolina);
        resultado = findViewById(R.id.textResultado);

    }

    public void calcular(View view){
        String alcoolValue = alcool.getText().toString();
        String gasolinaValue = gasolina.getText().toString();

        Boolean validarCampos = validarCampos(alcoolValue, gasolinaValue);

        if(validarCampos){

            Double alcoolDouble = Double.parseDouble(alcoolValue);
            Double gasolinaDouble = Double.parseDouble(gasolinaValue);

            Double resultadoDouble = (alcoolDouble * 11)/7.5;
            if(resultadoDouble > gasolinaDouble){
                resultado.setText("Melhor utilizar Gasolina");
            }else{
                resultado.setText("Melhor utilizar Álcool");
            }
        }else{
            resultado.setText("Valores inválidos");
        }
    }

    public Boolean validarCampos(String alcoolValue, String gasolinaValue){
        Boolean validarCampos = true;

        if(alcoolValue == null || alcoolValue.equals("")){
            validarCampos = false;
        }
        else if(gasolinaValue == null || gasolinaValue.equals("")){
            validarCampos = false;
        }

        return validarCampos;
    }


}