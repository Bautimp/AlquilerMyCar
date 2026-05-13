package com.example.alquilermycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AlquilerActivity extends AppCompatActivity {

    private EditText etCliente, etDias;
    private Spinner spPago;
    private TextView tvPrecioDia, tvVehiculoSeleccionado;

    private String vehiculo;
    private int posicion;
    private double precioPorDia = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alquiler);

        etCliente = findViewById(R.id.et_cliente);
        etDias = findViewById(R.id.et_dias);
        spPago = findViewById(R.id.sp_pago);
        // Asegúrate de agregar estos dos TextView en tu activity_alquiler.xml
        tvPrecioDia = findViewById(R.id.tv_precio_dia);
        tvVehiculoSeleccionado = findViewById(R.id.tv_vehiculo_seleccionado);

        // Obtener datos del vehículo seleccionado de la pantalla anterior
        vehiculo = getIntent().getStringExtra("vehiculo");
        posicion = getIntent().getIntExtra("position", 0);
        precioPorDia = Double.parseDouble(getIntent().getStringExtra("precio"));
        if(vehiculo == null) {
            vehiculo = "Vehículo Modelo Genérico";
        }

        tvVehiculoSeleccionado.setText("Vehículo seleccionado: \n" + vehiculo);
        tvPrecioDia.setText("Precio por día: $" + precioPorDia);

        // Configurar Spinner de Formas de Pago
        String[] formasPago = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Transferencia"};
        ArrayAdapter<String> adapterPago = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, formasPago);
        spPago.setAdapter(adapterPago);
    }

    // Método enlazado al botón de Confirmar
    public void verResumen(View view) {
        String cliente = etCliente.getText().toString();
        String diasStr = etDias.getText().toString();
        String pago = spPago.getSelectedItem().toString();

        // Validación de campos vacíos
        if (cliente.isEmpty() || diasStr.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int dias = Integer.parseInt(diasStr);
        if (dias <= 0) {
            Toast.makeText(this, "La cantidad de días debe ser mayor a 0", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dias > 31){
            Toast.makeText(this, "RENTaCAR permite hasta 31 dias de alquilar sus vehiulos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cálculo del monto total según la fórmula del TP
        double total = dias * precioPorDia;

        // Empaquetar y pasar los datos a la actividad de Resumen (ResumenActivity)
        Intent intent = new Intent(this, ResumenActivity.class);
        intent.putExtra("cliente", cliente);
        intent.putExtra("vehiculo", vehiculo);
        intent.putExtra("dias", dias);
        intent.putExtra("pago", pago);
        intent.putExtra("total", total);
        intent.putExtra("position", posicion);
        startActivity(intent);
    }

    // Funciones de navegación requeridas por el TP
    public void volverAlVehiculo(View v) {
        finish(); // Cancela/Vuelve a la pantalla de Swipe del Vehículo
    }

    public void volverAlInicio(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    // Método para volver a la pantalla inmediatamente anterior
    public void volverAtras(View view) {
        finish(); // Cierra la actividad actual
    }
}