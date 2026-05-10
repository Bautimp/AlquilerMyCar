package com.example.alquilermycar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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

    private Integer[] listaVehiculos = {
            R.drawable.auto1, R.drawable.auto2, R.drawable.auto3, R.drawable.auto4
    };

    private String[] caracteristicasVehiculos = {
            "Marca: Ford\nModelo: Fiesta\nAño: 2019\nPrecio: $15000/día",
            "Marca: Toyota\nModelo: Corolla\nAño: 2021\nPrecio: $18000/día",
            "Marca: Fiat\nModelo: Cronos\nAño: 2022\nPrecio: $16000/día",
            "Marca: Volkswagen\nModelo: Gol\nAño: 2020\nPrecio: $14000/día"
    };

    // Descripciones personalizadas para cada vehículo
    private String[] descripcionesVehiculos = {
            "Vehículo compacto ideal para la ciudad. Muy bajo consumo de combustible y fácil de estacionar.",
            "Sedán espacioso y sumamente confortable, perfecto para viajes largos en familia con gran seguridad.",
            "Diseño moderno con excelente capacidad de baúl. Gran relación calidad-precio para uso diario.",
            "El clásico hatchback, confiable, ágil y con un mantenimiento muy económico."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_swipe);

        viewPager = findViewById(R.id.viewPagerVehiculos);

        // 2. Pasamos el nuevo arreglo de descripciones al adaptador
        adapter = new VehiculoPagerAdapter(this, listaVehiculos, caracteristicasVehiculos, descripcionesVehiculos);
        viewPager.setAdapter(adapter);

        int pos = getIntent().getIntExtra("posicion", 0);
        viewPager.setCurrentItem(pos);
    }

    public void abrirFormularioAlquiler(View v) {
        Intent intent = new Intent(this, AlquilerActivity.class);
        int posicionActual = viewPager.getCurrentItem();
        intent.putExtra("vehiculo", "Vehículo Opción " + (posicionActual + 1));
        startActivity(intent);
    }

    private class VehiculoPagerAdapter extends PagerAdapter {
        private Context context;
        private Integer[] imagenes;
        private String[] caracteristicas;
        private String[] descripciones; // Variable para recibir las descripciones

        public VehiculoPagerAdapter(Context context, Integer[] imagenes, String[] caracteristicas, String[] descripciones) {
            this.context = context;
            this.imagenes = imagenes;
            this.caracteristicas = caracteristicas;
            this.descripciones = descripciones; // Asignación
        }

        @Override
        public int getCount() {
            return imagenes.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);

            // Imagen del auto
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imagenes[position]);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(600, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // Texto de Características Técnicas
            TextView tvCaracteristicas = new TextView(context);
            tvCaracteristicas.setText(caracteristicas[position]);
            tvCaracteristicas.setTextSize(18f);
            tvCaracteristicas.setPadding(20, 50, 20, 10);
            tvCaracteristicas.setGravity(Gravity.CENTER);

            // Texto para la descripción
            TextView tvDescripcion = new TextView(context);
            tvDescripcion.setText(descripciones[position]);
            tvDescripcion.setTextSize(16f);
            tvDescripcion.setPadding(40, 10, 40, 20);
            tvDescripcion.setGravity(Gravity.CENTER);
            tvDescripcion.setTypeface(null, Typeface.ITALIC); // Lo ponemos en cursiva para diferenciarlo

            // Añadimos todo al layout en orden
            layout.addView(imageView);
            layout.addView(tvCaracteristicas);
            layout.addView(tvDescripcion); // Se añade la nueva descripción debajo de las características

            container.addView(layout);

            return layout;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        // Botón para volver a la lista de vehículos de la categoría
        public void volverAtras(View view) {
            // Vaciamos el adaptador para liberar las fotos pesadas
            if(viewPager != null) {
                viewPager.setAdapter(null);
            }
            finish(); // Cerramos la actividad
        }
    }
}