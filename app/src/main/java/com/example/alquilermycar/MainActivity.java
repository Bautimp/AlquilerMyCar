package com.example.alquilermycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irACategorias(View v) {
        startActivity(new Intent(this, CategoriasActivity.class));
    }

    public void irAInformacion(View v) {
        startActivity(new Intent(this, InfoActivity.class));
    }

    public void irAHistorial(View v) {
        startActivity(new Intent(this, HistorialActivity.class));
    }
}