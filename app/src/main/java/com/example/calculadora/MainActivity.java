package com.example.calculadora;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculadora calculadora;
    private TextView mainDisplay;
    private boolean isOn = true;
    private StringBuilder currentInput = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculadora = new Calculadora();
        mainDisplay = findViewById(R.id.main);

        setButtonListeners();
    }

    private void setButtonListeners() {
        Button btnSiete = findViewById(R.id.btnSiete);
        Button btnOcho = findViewById(R.id.btnOcho);
        Button btnNueve = findViewById(R.id.btnNueve);
        Button btnMas = findViewById(R.id.btnMas);
        Button btnCuatro = findViewById(R.id.btnCuatro);
        Button btnCinco = findViewById(R.id.btnCinco);
        Button btnSeis = findViewById(R.id.btnSeis);
        Button btnMenos = findViewById(R.id.btnMenos);
        Button btnUno = findViewById(R.id.btnUno);
        Button btnDos = findViewById(R.id.btnDos);
        Button btnTres = findViewById(R.id.btnTres);
        Button btnPor = findViewById(R.id.btnPor);
        Button btnCero = findViewById(R.id.btnCero);
        Button btnIgual = findViewById(R.id.btnIgual);
        Button btnCE = findViewById(R.id.btnCE);
        Button btnOff = findViewById(R.id.btnOff);
        Button btnOn = findViewById(R.id.btnOn);
        Button btnPorcent = findViewById(R.id.btnPorcent);
        Button btnEntre = findViewById(R.id.btnEntre);

        setNumberButtonListener(btnSiete, "7");
        setNumberButtonListener(btnOcho, "8");
        setNumberButtonListener(btnNueve, "9");
        setNumberButtonListener(btnCuatro, "4");
        setNumberButtonListener(btnCinco, "5");
        setNumberButtonListener(btnSeis, "6");
        setNumberButtonListener(btnUno, "1");
        setNumberButtonListener(btnDos, "2");
        setNumberButtonListener(btnTres, "3");
        setNumberButtonListener(btnCero, "0");

        btnMas.setOnClickListener(v -> performOperation("+"));
        btnMenos.setOnClickListener(v -> performOperation("-"));
        btnPor.setOnClickListener(v -> performOperation("*"));
        btnEntre.setOnClickListener(v -> performOperation("/"));
        btnIgual.setOnClickListener(v -> performOperation("="));
        btnCE.setOnClickListener(v -> {
            calculadora.clearEntry();
            currentInput.setLength(0);
            mainDisplay.setText("");
        });

        // Botones On/Off
        btnOn.setOnClickListener(v -> {
            isOn = true;
            mainDisplay.setText("");
            currentInput.setLength(0);
        });

        btnOff.setOnClickListener(v -> {
            isOn = false;
            mainDisplay.setText("");
        });

        btnPorcent.setOnClickListener(v -> {
            if (isOn) {
                double value = calculadora.getResult();
                double percentage = value / 100;
                mainDisplay.setText(String.valueOf(percentage));
                calculadora.setValue(percentage);
            }
        });
    }

    private void setNumberButtonListener(Button button, String value) {
        button.setOnClickListener(v -> {
            if (isOn) {
                currentInput.append(value);
                mainDisplay.setText(currentInput.toString());

                if (currentInput.length() > 0) {
                    double currentValue = Double.parseDouble(currentInput.toString());
                    calculadora.setValue(currentValue);
                }
            }
        });
    }

    private void performOperation(String operation) {
        if (isOn) {
            calculadora.performOperation(operation);
            if (!operation.equals("CE")) {
                currentInput.setLength(0);
                mainDisplay.setText(String.valueOf(calculadora.getResult()));
            }
        }
    }
}
