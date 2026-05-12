package com.example.alquilermycar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class VehiculosListaActivity extends AppCompatActivity {

    public static int mSelected = 0;
    private GridView gridView;
    private String categoriaSeleccionada;

    public static Integer[] imagenesVehiculos = {
            R.drawable.auto1_min,
            R.drawable.auto2_min,
            R.drawable.auto3_min,
            R.drawable.auto4_min
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.galeria_imagenes);

        gridView = (GridView) findViewById(R.id.gridview_galeria);

        gridView.setAdapter(new imageAdapter(this));

        gridView.setSelection(mSelected);

        categoriaSeleccionada = getIntent().getStringExtra("CATEGORIA");

        if (categoriaSeleccionada != null) {

            setTitle("Vehículos: " + categoriaSeleccionada);

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

            return imagenesVehiculos.length;

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
                    VehiculosListaActivity.imagenesVehiculos[position]
            );

            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    mSelected = (Integer) v.getTag();

                    notifyDataSetChanged();

                    String index = String.valueOf(position);

                    Bundle extras = new Bundle();

                    extras.putString("position", index);

                    Intent in = new Intent(
                            mContexto,
                            DetalleVehiculoActivity.class
                    ).putExtras(extras);

                    mContexto.startActivity(in);

                }
            });

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