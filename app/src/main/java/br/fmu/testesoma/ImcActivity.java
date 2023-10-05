package br.fmu.testesoma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class ImcActivity extends AppCompatActivity {

    private EditText editTextAltura;
    private TextView textViewAltura;
    private TextView textViewPesoMin;
    private TextView textViewPesoMax;
    private SeekBar seekBar2;
    private double altura;
    private double pesoMin;
    private double pesoMax;
    private int imc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        editTextAltura = findViewById(R.id.editTextAltura);
        textViewAltura = findViewById(R.id.textViewAltura);
        textViewPesoMin = findViewById(R.id.textViewPesoMin);
        textViewPesoMax = findViewById(R.id.textViewPesoMax);
        seekBar2 = findViewById(R.id.seekBar2);
        editTextAltura.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int alturaInt;
                try {
                    alturaInt = Integer.parseInt(editTextAltura.getText().toString());
                } catch( NumberFormatException e ) {
                    alturaInt = 0;
                }
                altura = alturaInt/100.0;
                atualizarValores();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imc = progress;
                atualizarValores();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void atualizarValores() {
        switch (imc) {
            case 0:
                pesoMin = 0;
                pesoMax = 17 * altura*altura;
                break;
            case 1:
                pesoMin = 17 * altura * altura;
                pesoMax = 25 * altura * altura;
                break;
            case 2:
                pesoMin = 25 * altura * altura;
                pesoMax = 30 * altura * altura;
                break;
            case 3:
                pesoMin = 30 * altura * altura;
                pesoMax = Double.POSITIVE_INFINITY;
                break;
        }
        textViewAltura.setText(Double.toString(altura));
        textViewPesoMin.setText(Double.toString(pesoMin));
        textViewPesoMax.setText(Double.toString(pesoMax));
    }
}