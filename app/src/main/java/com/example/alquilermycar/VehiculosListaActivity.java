package com.example.alquilermycar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class VehiculosListaActivity extends AppCompatActivity {

    private GridView gridVehiculos;
    private String categoriaSeleccionada;

    // Arreglo simulado con las imágenes de los vehículos en miniatura.
    // Para el TP, usamos los íconos por defecto como ejemplo (mínimo 4).
    public Integer[] imagenesVehiculos = {
            R.drawable.auto1_min, R.drawable.auto2_min, R.drawable.auto3_min, R.drawable.auto4_min
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos_lista);

        gridVehiculos = findViewById(R.id.grid_vehiculos);

        // Recibir la categoría del Intent para mostrar en el título
        categoriaSeleccionada = getIntent().getStringExtra("CATEGORIA");
        if (categoriaSeleccionada != null) {
            setTitle("Vehículos: " + categoriaSeleccionada);
        }

        // Asignar el adaptador al GridView
        gridVehiculos.setAdapter(new VehiculoAdapter(this));

        // Click en la miniatura para abrir la pantalla de Detalle/Swipe
        gridVehiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VehiculosListaActivity.this, DetalleVehiculoActivity.class);
                intent.putExtra("posicion", position);
                intent.putExtra("CATEGORIA", categoriaSeleccionada);
                startActivity(intent);
            }
        });
    }

    // Funciones de navegación requeridas por el TP
    public void volverACategorias(View v) {
        finish(); // Vuelve a la pantalla anterior
    }

    public void volverAlInicio(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish(); // Destruye esta actividad y vuelve al Home
    }

    // Adaptador interno para el GridView (como en el Ejercicio 3)
    public class VehiculoAdapter extends BaseAdapter {
        private Context mContexto;

        public VehiculoAdapter(Context c) {
            mContexto = c;
        }

        @Override
        public int getCount() {
            return imagenesVehiculos.length; // Retorna la cantidad de miniaturas
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContexto);
                imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imagenesVehiculos[position]);
            return imageView;
        }
    }
    // Método para volver a la pantalla inmediatamente anterior
    public void volverAtras(View view) {
        finish(); // Cierra la actividad actual
    }
}