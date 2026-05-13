package com.example.alquilermycar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class VehiculosListaActivity extends AppCompatActivity {

    public static int mSelected = 0;
    private GridView gridView;
    static Integer[] imagenesAMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.galeria_imagenes);


        String categoriaRecibida = getIntent().getStringExtra("CATEGORIA");
        if (categoriaRecibida != null) {
            setTitle("Vehículos: " + categoriaRecibida);
        }

        cargarDatosPorCategoria(categoriaRecibida);

        gridView = (GridView) findViewById(R.id.gridview_galeria);

        gridView.setAdapter(new imageAdapter(this));

        gridView.setSelection(mSelected);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VehiculosListaActivity.this, DetalleVehiculoActivity.class);
                intent.putExtra("posicion", position);
                // Pasar la categoría a para la próxima pantalla
                intent.putExtra("CATEGORIA", categoriaRecibida);
                startActivity(intent);
            }
        });

    }

    private void cargarDatosPorCategoria(String categoria) {
        switch (categoria) {
            case "Económicos":
                imagenesAMostrar = new Integer[]{
                        R.drawable.auto1_min, R.drawable.auto2_min, R.drawable.auto3_min, R.drawable.auto4_min
                };
                break;

            case "SUV":
                imagenesAMostrar = new Integer[]{
                        R.drawable.auto5_min, R.drawable.auto6_min, R.drawable.auto7_min, R.drawable.auto8_min
                };
                break;

            case "Premium":
                imagenesAMostrar = new Integer[]{
                        R.drawable.auto9_min, R.drawable.auto10_min, R.drawable.auto11_min, R.drawable.auto12_min
                };
                break;

            case "Todoterreno":
                imagenesAMostrar = new Integer[]{
                        R.drawable.auto13_min, R.drawable.auto14_min, R.drawable.auto15_min, R.drawable.auto16_min
                };
                break;
        }
    }

    public void volverACategorias(View v) {

        finish();

    }

    public void volverAlInicio(View v) {

        Intent intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);

        finish();

    }

    public void volverAtras(View view) {

        finish();

    }

    public class imageAdapter extends BaseAdapter {

        private Context mContexto;

        public imageAdapter(Context c) {

            mContexto = c;

        }

        @Override
        public int getCount() {

            return imagenesAMostrar.length;

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

                imageView.setLayoutParams(
                        new GridView.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                130
                        )
                );

                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                imageView.setCropToPadding(true);

                imageView.setPadding(2,2,2,2);

            } else {

                imageView = (ImageView) convertView;

            }

            imageView.setImageResource(
                    VehiculosListaActivity.imagenesAMostrar[position]
            );

            try {

                imageView.setTag(position);

                if (position == mSelected) {

                    imageView.setBackgroundColor(
                            Color.parseColor("#ff6203")
                    );

                } else {

                    imageView.setBackgroundColor(
                            Color.TRANSPARENT
                    );

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

            return imageView;
        }
    }
}