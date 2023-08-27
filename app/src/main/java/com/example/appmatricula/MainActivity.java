package com.example.appmatricula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText tvCostoCarrera, tvPension, tvGastosAdicionales, etNombreAlumno, etEscuela, etCarrera ;
    private CheckBox chcBxCarnetBiblioteca, chcBxCarnetMedioPasaje;
    private RadioButton num4cuotas, num5cuotas, num6cuotas;
    private Button btnCalcular, btnImprimir;
    private TextView TotalPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombreAlumno = findViewById(R.id.idAlumno);
        etEscuela = findViewById(R.id.idEscuela);
        etCarrera = findViewById(R.id.idCarerra);

        tvCostoCarrera = findViewById(R.id.tvCostoCarrera);
        tvPension = findViewById(R.id.tvPension);
        tvGastosAdicionales = findViewById(R.id.tvGastosAdisoles);

        chcBxCarnetBiblioteca = findViewById(R.id.chcBxCarnetBibli2);
        chcBxCarnetMedioPasaje = findViewById(R.id.chcBxCarnetMedioPasaje);

        num4cuotas = findViewById(R.id.num4cuotas);
        num5cuotas = findViewById(R.id.num5cuotas);
        num6cuotas = findViewById(R.id.num6cuotas);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnImprimir = findViewById(R.id.btnImprimir);

        TotalPagar = findViewById(R.id.TotalPagar);

        num4cuotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num5cuotas.setChecked(false);
                num6cuotas.setChecked(false);
            }
        });

        num5cuotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num4cuotas.setChecked(false);
                num6cuotas.setChecked(false);
            }
        });

        num6cuotas.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num4cuotas.setChecked(false);
                num5cuotas.setChecked(false);
            }
        });

        chcBxCarnetBiblioteca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chcBxCarnetMedioPasaje.setChecked(false);
            }
        });

        chcBxCarnetMedioPasaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chcBxCarnetBiblioteca.setChecked(false);
            }
        });




        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularTotal();
            }
        });

        btnImprimir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                imprimirRecibo();

            }
        });

    }

    private void calcularTotal(){
        double costoCarrera = Double.parseDouble(tvCostoCarrera.getText().toString());
        double pension = Double.parseDouble(tvPension.getText().toString());
        double gastosAdicionales = Double.parseDouble(tvGastosAdicionales.getText().toString());
        double totalPagar = costoCarrera + pension + gastosAdicionales;

        if (chcBxCarnetBiblioteca.isChecked()){
            totalPagar+=25.0;
        }
        if (chcBxCarnetMedioPasaje.isChecked()){
            totalPagar +=22.0;
        }

        int numCuotas = 4;
        if (num5cuotas.isChecked()){
            numCuotas = 5;
        } else if (num6cuotas.isChecked()){
            numCuotas = 6;
        }
        totalPagar +=costoCarrera * (0.12 * numCuotas);
        TotalPagar.setText("Total a Pagar: S/." + totalPagar);
    }

    private void imprimirRecibo() {
        String nombreAlumno = etNombreAlumno.getText().toString();
        String escuela = etEscuela.getText().toString();
        String carrera = etCarrera.getText().toString();
        double costoCarrera = Double.parseDouble(tvCostoCarrera.getText().toString());
        double pension = Double.parseDouble(tvPension.getText().toString());
        double gastosAdicionales = Double.parseDouble(tvGastosAdicionales.getText().toString());
        double totalPagar = Double.parseDouble(TotalPagar.getText().toString().replace("Total a Pagar: S/.", ""));

        boolean carnetBiblioteca = chcBxCarnetBiblioteca.isChecked();
        boolean carnetMedioPasaje = chcBxCarnetMedioPasaje.isChecked();

        int numCuotas = 4;
        if (num5cuotas.isChecked()) {
            numCuotas = 5;
        } else if (num6cuotas.isChecked()) {
            numCuotas = 6;
        }

        Intent intent = new Intent(this, ImprimirActivity.class);
        intent.putExtra("nombreAlumno", nombreAlumno);
        intent.putExtra("escuela", escuela);
        intent.putExtra("carrera", carrera);
        intent.putExtra("costoCarrera", costoCarrera);
        intent.putExtra("pension", pension);
        intent.putExtra("gastosAdicionales", gastosAdicionales);
        intent.putExtra("totalPagar", totalPagar);
        intent.putExtra("carnetBiblioteca", carnetBiblioteca);
        intent.putExtra("carnetMedioPasaje", carnetMedioPasaje);
        intent.putExtra("numCuotas", numCuotas);
        startActivity(intent);
    }
}


