package com.example.alquilermycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Aquí podrías configurar programáticamente textos si no los pusiste en el XML
    }

    // Navegación de vuelta a la actividad principal
    public void irAMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Limpia la pila para volver al inicio
        startActivity(intent);
        finish();
    }

    // Navegación hacia las Categorías
    public void irACategorias(View v) {
        Intent intent = new Intent(this, CategoriasActivity.class);
        startActivity(intent);
        finish(); // Cerramos Info para ir a Categorías
    }
}