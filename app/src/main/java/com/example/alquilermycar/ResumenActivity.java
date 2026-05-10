package com.example.alquilermycar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResumenActivity extends AppCompatActivity {

    // These variables should be initialized from Intent extras or fields
    private String nombreCliente = "";
    private String modeloVehiculo = "";
    private int cantidadDias = 0;
    private double montoTotal = 0.0;
    private String metodoPago = "";

    private TextView tvResumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        // 2. Enlazar con el XML
        tvResumen = findViewById(R.id.tv_resumen_datos);
        // Example of getting data from Intent
        if (getIntent().getExtras() != null) {
            nombreCliente = getIntent().getStringExtra("cliente");
            modeloVehiculo = getIntent().getStringExtra("vehiculo");
            cantidadDias = getIntent().getIntExtra("dias", 0);
            montoTotal = getIntent().getDoubleExtra("total", 0.0);
            metodoPago = getIntent().getStringExtra("pago");
            // 3. Crear el texto del resumen y mostrarlo
            String resumenText = "Resumen del Alquiler:\n\n" +
                    "Cliente: " + nombreCliente + "\n" +
                    "Vehículo: " + modeloVehiculo + "\n" +
                    "Días: " + cantidadDias + "\n" +
                    "Pago: " + metodoPago + "\n" +
                    "Total: $" + montoTotal;

            tvResumen.setText(resumenText);
        }
    }

    public void guardarAlquiler(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "MyCarDB", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("cliente", nombreCliente);
        registro.put("vehiculo", modeloVehiculo);
        registro.put("dias", cantidadDias);
        registro.put("total", montoTotal);
        registro.put("pago", metodoPago);

        long resultado = db.insert("alquileres", null, registro);
        db.close();

        if (resultado != -1) {
            Toast.makeText(this, "Alquiler Registrado", Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
        }
    }
}
