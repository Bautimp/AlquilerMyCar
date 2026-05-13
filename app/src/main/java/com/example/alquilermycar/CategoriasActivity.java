package com.example.alquilermycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CategoriasActivity extends AppCompatActivity {

    private ListView lvCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        lvCategorias = findViewById(R.id.list_categorias);

        // Definición de las 4 categorías requeridas
        ArrayList<String> categorias = new ArrayList<>();
        categorias.add("Económicos");
        categorias.add("SUV");
        categorias.add("Premium");
        categorias.add("Todoterreno");

        // El adaptador actúa como puente entre los datos y la vista
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categorias);
        lvCategorias.setAdapter(adapter);

        // Evento para seleccionar una categoría y pasar a la lista de vehículos
        lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String categoriaSeleccionada = (String) parent.getItemAtPosition(position);

                // Uso de Intent explícito para navegar
                Intent intent = new Intent(CategoriasActivity.this, VehiculosListaActivity.class);
                // Pasar el nombre de la categoría como extra
                intent.putExtra("CATEGORIA", categoriaSeleccionada);
                startActivity(intent);
            }
        });
    }

    // Botón para volver a la página principal
    public void volverAlInicio(View v) {
        finish(); // Finaliza la actividad actual para regresar a la anterior
    }

    // Método para volver a la pantalla inmediatamente anterior
    public void volverAtras(View view) {
        finish(); // Cierra la actividad actual
    }
}