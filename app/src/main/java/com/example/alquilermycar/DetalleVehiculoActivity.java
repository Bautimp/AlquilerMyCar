package com.example.alquilermycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

// lógica Swipe
public class DetalleVehiculoActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private VehiculoPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_swipe);

        viewPager = findViewById(R.id.viewPagerVehiculos);
        adapter = new VehiculoPagerAdapter(this, listaVehiculos);
        viewPager.setAdapter(adapter);

        // Obtener índice seleccionado del Intent anterior
        int pos = getIntent().getIntExtra("posicion", 0);
        viewPager.setCurrentItem(pos);
    }

    // Botón para iniciar el proceso de alquiler desde el detalle
    public void abrirFormularioAlquiler(View v) {
        Intent intent = new Intent(this, AlquilerActivity.class);
        // Pasar datos del vehículo actual al formulario
        startActivity(intent);
    }
}