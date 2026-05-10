package com.example.alquilermycar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistorialActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        listView = findViewById(R.id.lv_historial);
        mostrarRegistros();
    }

    public void mostrarRegistros() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "MyCarDB", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor fila = db.rawQuery("select * from alquileres", null);
        ArrayList<String> lista = new ArrayList<>();

        if (fila.moveToFirst()) {
            do {
                // Se asumen las columnas según el orden de inserción en ResumenActivity
                lista.add("Cliente: " + fila.getString(1) + " - Total: $" + fila.getString(4));
            } while (fila.moveToNext());
        }
        fila.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
        db.close();
    }
}
