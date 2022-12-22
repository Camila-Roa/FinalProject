package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.finalproject.Adaptadores.ProductoAdapter;
import com.example.finalproject.DB.DBFirebase;
import com.example.finalproject.Entidades.Producto;


import java.util.ArrayList;

import java.util.ArrayList;

public class Catalogo extends AppCompatActivity {
    private DBFirebase dbFirebase;
    private ListView listViewProducts;
    private ArrayList<Producto> arrayProductos;
    private ProductoAdapter productoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);


        listViewProducts = (ListView) findViewById(R.id.listViewProductps);
        arrayProductos = new ArrayList<>();
        dbFirebase = new DBFirebase();
        
        /*Producto producto1 = new Producto("Buena Calidad",
                "5000",565,"","","");
        Producto producto2 = new Producto("LLantas","Buena Calidad",
                5000,"","","");

        arrayProductos.add(producto1);
        arrayProductos.add(producto2);
        */

        productoAdapter = new ProductoAdapter(this, arrayProductos);
        listViewProducts.setAdapter(productoAdapter);
        dbFirebase.getData(productoAdapter);
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()){
                case R.id.itemAdd:
                    intent = new Intent(getApplicationContext(), Form.class);
                    startActivity(intent);
                    return true;
                case R.id.itemMap:
                    intent = new Intent(getApplicationContext(), Maps.class);
                    startActivity(intent);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }

    }
}
