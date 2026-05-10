package com.example.alquilermycar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DetalleVehiculoActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private VehiculoPagerAdapter adapter;

    // 1. Declarar listaVehiculos (Imágenes de ejemplo)
    private Integer[] listaVehiculos = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };

    // Declarar las descripciones para cada vehículo
    private String[] descripcionesVehiculos = {
            "Marca: Ford\nModelo: Fiesta\nAño: 2019\nPrecio: $15000/día",
            "Marca: Toyota\nModelo: Corolla\nAño: 2021\nPrecio: $18000/día",
            "Marca: Fiat\nModelo: Cronos\nAño: 2022\nPrecio: $16000/día",
            "Marca: Volkswagen\nModelo: Gol\nAño: 2020\nPrecio: $14000/día"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_swipe);

        viewPager = findViewById(R.id.viewPagerVehiculos);

        // 2. Instanciar el adaptador pasándole las listas
        adapter = new VehiculoPagerAdapter(this, listaVehiculos, descripcionesVehiculos);
        viewPager.setAdapter(adapter);

        // Obtener índice seleccionado del Intent anterior (la grilla)
        int pos = getIntent().getIntExtra("posicion", 0);
        viewPager.setCurrentItem(pos);
    }

    // Botón para iniciar el proceso de alquiler desde el detalle
    public void abrirFormularioAlquiler(View v) {
        Intent intent = new Intent(this, AlquilerActivity.class);

        // Determinar qué vehículo se está viendo actualmente en la pantalla
        int posicionActual = viewPager.getCurrentItem();
        intent.putExtra("vehiculo", "Vehículo Opción " + (posicionActual + 1));

        startActivity(intent);
    }

    // ====================================================================
    // 3. CREAR LA CLASE VehiculoPagerAdapter AQUÍ MISMO (COMO CLASE INTERNA)
    // ====================================================================
    private class VehiculoPagerAdapter extends PagerAdapter {
        private Context context;
        private Integer[] imagenes;
        private String[] descripciones;

        public VehiculoPagerAdapter(Context context, Integer[] imagenes, String[] descripciones) {
            this.context = context;
            this.imagenes = imagenes;
            this.descripciones = descripciones;
        }

        @Override
        public int getCount() {
            return imagenes.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object; // Verifica si la vista pertenece al objeto actual
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            // Creamos un LinearLayout por código para organizar imagen y texto
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);

            // Creamos el ImageView para la foto del auto
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imagenes[position]);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(600, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // Creamos el TextView para las características
            TextView textView = new TextView(context);
            textView.setText(descripciones[position]);
            textView.setTextSize(18f);
            textView.setPadding(20, 50, 20, 20);
            textView.setGravity(Gravity.CENTER);

            // Añadimos la imagen y el texto al layout
            layout.addView(imageView);
            layout.addView(textView);

            // Añadimos todo el conjunto al ViewPager
            container.addView(layout);

            return layout;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            // Se elimina la vista cuando se hace swipe y queda fuera de pantalla
            container.removeView((View) object);
        }
    }
}