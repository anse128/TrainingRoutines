package com.example.sebastian.trainingroutines;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class ComidasActivity extends AppCompatActivity {

    BasedeDatos db;
    ListView lvComidas;
    List<String> item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comidas);

        lvComidas = (ListView) findViewById(R.id.lvcomidas);

        mostrarlista();

        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_speed_dial);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_add:
                        Intent intent = new Intent(ComidasActivity.this, LectorCodigosActivity.class);
                        intent.putExtra("AÑADIR",1);
                        ComidasActivity.this.finish();
                        startActivity(intent);
                        break;
                    case R.id.action_create:
                        Intent intent2 = new Intent(ComidasActivity.this, LectorCodigosActivity.class);
                        intent2.putExtra("AÑADIR",2);
                        ComidasActivity.this.finish();
                        startActivity(intent2);
                        break;
                    case R.id.action_manual:
                        Intent intent3 = new Intent(ComidasActivity.this, AgregarAlimentoActivity.class);
                        startActivity(intent3);
                        ComidasActivity.this.finish();
                        break;
                }
                return false;
            }
        });
    }

    private void mostrarlista(){
        db = new BasedeDatos(this);
        final Cursor cursor = db.getComidas();
        item = new ArrayList<String>();
        String title = "", content = "";
        if (cursor.moveToFirst()){
            do{
                title = cursor.getString(1);
                //content = cursor.getString(2);
                item.add(title+" ");
                //item.add(content+" ");
            }while (cursor.moveToNext());
        }
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, item);
        lvComidas.setAdapter(adaptador);

        lvComidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent intent = new Intent(ComidasActivity.this, AlimentoDetalleActivity.class);
                String nombre, calorias, proteinas, carbohidratos, grasas;
                nombre = cursor.getString(1);
                calorias = cursor.getString(2);
                proteinas = cursor.getString(3);
                carbohidratos = cursor.getString(4);
                grasas = cursor.getString(5);

                intent.putExtra("NOMBRE",nombre);
                intent.putExtra("CALORIAS",calorias);
                intent.putExtra("PROTEINAS",proteinas);
                intent.putExtra("CARBOHIDRATOS",carbohidratos);
                intent.putExtra("GRASAS",grasas);
                startActivity(intent);
            }
        });


    }
}
