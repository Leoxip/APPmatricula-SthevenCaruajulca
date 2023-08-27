package com.example.appmatricula;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
public class ImprimirActivity extends AppCompatActivity {

    private TextView tvNombreAlumno, tvEscuela, tvCarrera, tvCostoCarrera, tvPension, tvGastosAdicionales,
            tvTotalPagar, tvCarnetBiblioteca, tvCarnetMedioPasaje, tvNumCuotas;

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprimir);

        tvNombreAlumno = findViewById(R.id.tvNombreAlumno);
        tvEscuela = findViewById(R.id.tvEscuela);
        tvCarrera = findViewById(R.id.tvCarrera);
        tvCostoCarrera = findViewById(R.id.tvCostoCarrera);
        tvPension = findViewById(R.id.tvPension);
        tvGastosAdicionales = findViewById(R.id.tvGastosAdicionales);
        tvTotalPagar = findViewById(R.id.tvTotalPagar);
        tvCarnetBiblioteca = findViewById(R.id.tvCarnetBiblioteca);
        tvCarnetMedioPasaje = findViewById(R.id.tvCarnetMedioPasaje);
        tvNumCuotas = findViewById(R.id.tvNumCuotas);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombreAlumno = extras.getString("nombreAlumno");
            String escuela = extras.getString("escuela");
            String carrera = extras.getString("carrera");
            double costoCarrera = extras.getDouble("costoCarrera");
            double pension = extras.getDouble("pension");
            double gastosAdicionales = extras.getDouble("gastosAdicionales");
            double totalPagar = extras.getDouble("totalPagar");
            boolean carnetBiblioteca = extras.getBoolean("carnetBiblioteca");
            boolean carnetMedioPasaje = extras.getBoolean("carnetMedioPasaje");
            int numCuotas = extras.getInt("numCuotas");

            tvNombreAlumno.setText(nombreAlumno);
            tvEscuela.setText(escuela);
            tvCarrera.setText(carrera);
            tvCostoCarrera.setText("S/. " + String.valueOf(costoCarrera));
            tvPension.setText("S/. " + String.valueOf(pension));
            tvGastosAdicionales.setText("S/. " + String.valueOf(gastosAdicionales));
            tvTotalPagar.setText("S/. " + String.valueOf(totalPagar));
            tvCarnetBiblioteca.setText(carnetBiblioteca ? "Sí" : "No");
            tvCarnetMedioPasaje.setText(carnetMedioPasaje ? "Sí" : "No");
            tvNumCuotas.setText(String.valueOf(numCuotas));

            Button btnVolver = findViewById(R.id.btnVolver);
            btnVolver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Vuelve a MainActivity
                    onBackPressed();// Cierra la actividad actual para que no quede en la pila
                }
            });

            Button btnCancelar = findViewById(R.id.btnCancelar);
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ImprimirActivity.this, MainActivity.class);
                    intent.putExtra("cancelado", true); // Agrega una bandera para indicar cancelación
                    startActivity(intent);
                    finish();
                }
            });

        }
    }

}
