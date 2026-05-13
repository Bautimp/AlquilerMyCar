package com.example.alquilermycar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
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


    // Declarar listaVehiculos

    private Integer[] fotosSwipe;

    private ViewPager newsPager;
    private String[] imagesDescriptions;
    private String[] swipeDescriptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_imagenes);
        int index = getIntent().getIntExtra("posicion", 0);
        String categoriaRecibida = getIntent().getStringExtra("CATEGORIA");

        //Seteo de descripciones y texto del swipe con la información de array
        filtrarVehiculosParaSwipe(categoriaRecibida);

        //Desde acá primero crear la clase SwipeImagePagerAdapter
        SwipeImagePagerAdapter swipeNewsAdapter = new SwipeImagePagerAdapter();
        newsPager = (ViewPager) findViewById(R.id.swipe_pager);
        newsPager.setAdapter(swipeNewsAdapter);
        newsPager.setCurrentItem(index);


        newsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                VehiculosListaActivity.mSelected = i;
            }
            @Override
            public void onPageSelected(int i) {
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
    /*Creamos SwipeImagePagerAdapter. Donde utilizaremos el layout "mostrar_imagenes" para
    cargar la imagen original y los textos de descripción de la imagen*/
    private class SwipeImagePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return VehiculosListaActivity.imagenesAMostrar.length;
        }
        public Object instantiateItem (ViewGroup collection, int position){
            //LayoutInflater instancia un archive XML en sus correspondientes objetos
            LayoutInflater inflater = (LayoutInflater)
                    collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Referenciamos al layout mostrar_imagenes
            View view = inflater.inflate(R.layout.mostrar_imagenes, null);
            //Referenciamos al objeto ImageView del layout "mostrar_imagenes"
            ImageView imageView = (ImageView) view.findViewById(R.id.galeria_imagenes);
            /*Asignamos a cada ImageView una imagen de nuestro "Array" de recursos, utilizamos
            "setImageResource* ya que nuestras imágenes están almacenadas en una carpeta de recursos
            en nuestro proyecto.
            "Images" es un Array de enteros, ya que almacena el Id del recurso, no la imagen en si.*/
            imageView.setImageResource(fotosSwipe[position]);
            /*Referenciamos el objeto TextView del layout "mostrar_imagenes", para colocar la
            descripción de la imagen.*/
            TextView imageDescription =
                    (TextView)view.findViewById(R.id.imagen_descripcion);
            //Asignamos el text descriptivo de la imagen del objeto TextView
            imageDescription.setText(imagesDescriptions[position]);
            imageDescription.setTextColor(Color.parseColor("#FFFFFF"));

            TextView swipeDescription =
                    (TextView)view.findViewById(R.id.swipe_descripcion);

            swipeDescription.setText(swipeDescriptions[position]);
            swipeDescription.setTextColor(Color.parseColor("#FFFFFF"));


//Adicionamos el "view" que hemos creado con los objetos ImageView y TextView a la coleccion
            collection.addView(view,0);
            return view;
        } // public Object instantiateItem (ViewGroup collection, int position){

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {

            collection.removeView((View) view);

        }

        @Override
        public boolean isViewFromObject(View view, Object o) {

            return (view == o);

        }

    }

    // Botón para iniciar el proceso de alquiler desde el detalle
    public void abrirFormularioAlquiler(View v) {
        Intent intent = new Intent(this, AlquilerActivity.class);

        // Determinar qué vehículo se está viendo actualmente en la pantalla
        int posicionActual = newsPager.getCurrentItem();
        intent.putExtra("vehiculo", "Vehículo Opción " + (posicionActual + 1));
        intent.putExtra("position", posicionActual);
        startActivity(intent);
    }

    private void filtrarVehiculosParaSwipe(String categoria) {
        // Obtenemos los recursos de la aplicación
        android.content.res.Resources res = getResources();

        switch (categoria) {
            case "Económicos":
                fotosSwipe = new Integer[]{
                        R.drawable.auto1, R.drawable.auto2, R.drawable.auto3, R.drawable.auto4
                };
                // Usamos las variables correctas del adaptador
                imagesDescriptions = res.getStringArray(R.array.economicos_images_descriptions);
                swipeDescriptions = res.getStringArray(R.array.economicos_swipe_descriptions);
                break;

            case "SUV / Todo Terreno":
                fotosSwipe = new Integer[]{
                        R.drawable.auto1, R.drawable.auto2, R.drawable.auto3, R.drawable.auto4
                };
                imagesDescriptions = res.getStringArray(R.array.suv_images_descriptions);
                swipeDescriptions = res.getStringArray(R.array.suv_swipe_descriptions);
                break;

            case "Lujo / Premium":
                fotosSwipe = new Integer[]{
                        R.drawable.auto1, R.drawable.auto2, R.drawable.auto3, R.drawable.auto4
                };
                imagesDescriptions = res.getStringArray(R.array.lujo_images_descriptions);
                swipeDescriptions = res.getStringArray(R.array.lujo_swipe_descriptions);
                break;

            case "Furgonetas / Carga":
                fotosSwipe = new Integer[]{
                        R.drawable.auto1, R.drawable.auto2, R.drawable.auto3, R.drawable.auto4
                };
                imagesDescriptions = res.getStringArray(R.array.furgonetas_images_descriptions);
                swipeDescriptions = res.getStringArray(R.array.furgonetas_swipe_descriptions);
                break;
        }
    }

    // modificar el botón de volver para vaciar la memoria primero
    public void volverAtras(View view) {
        if (newsPager != null) {
            newsPager.setAdapter(null);
        }
        finish();
    }

}


