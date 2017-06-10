package com.example.sebastian.trainingroutines;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AlimentacionActivity extends AppCompatActivity {

    TextView tvCalorias, tvProteinas, tvCarbohidratos, tvGrasas;
    Button btnAlimento;
    ListView lvAlimentacion;
    String lector;
    int Caloriasbd;
    List<String> item = null;
    BasedeDatos db;
    int caloriassum=0;
    //String Nombre, Calorias, Proteinas, Carbohidratos, Grasas;

    //final BasedeDatos basedeDatos = new BasedeDatos(getApplicationContext());

    //SQLiteDatabase db = basedeDatos.getReadableDatabase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentacion);

        tvCalorias = (TextView) findViewById(R.id.tvcalorias);
        //tvProteinas = (TextView) findViewById(R.id.tvproteinas);
        //tvCarbohidratos = (TextView) findViewById(R.id.tvcarbohidratos);
        //tvGrasas = (TextView) findViewById(R.id.tvgrasas);
        btnAlimento = (Button) findViewById(R.id.btnalimento);

        lvAlimentacion = (ListView) findViewById(R.id.lvalimentacion);
        mostrarlista();

        btnAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlimentacionActivity.this, ComidasActivity.class);
                startActivity(intent);
            }
        });

        System.out.println("lector = " + lector);
        final BasedeDatos basedeDatos = new BasedeDatos(getApplicationContext());
        SQLiteDatabase db = basedeDatos.getReadableDatabase();
        String[] argsel = {"1"};
        String[] projection = {BasedeDatos.COLUMNA_ID_P,
                BasedeDatos.COLUMNA_CALORIAS_P};
        Cursor c = db.query(BasedeDatos.NOMBRE_TABLA2, projection, BasedeDatos.COLUMNA_ID_P + "=?", argsel, null, null, null);
        Log.i("AlimentacionActivity:","--------------------------------------------");


        if(c.moveToFirst()){
            Caloriasbd= Integer.parseInt((c.getString(1)));
        }else{
            Toast.makeText(this,"No se ha registrado el código de barras en la aplicación",Toast.LENGTH_SHORT).show();
        }
        Caloriasbd -= caloriassum;
        //String hola = String.valueOf(Caloriasbd);
        tvCalorias.setText(String.valueOf(Caloriasbd));

        /*
        if (extras != null) {
            lector = extras.getString("BUSCAR");
            System.out.println("lector = " + lector);
            final BasedeDatos basedeDatos = new BasedeDatos(getApplicationContext());
            SQLiteDatabase db = basedeDatos.getReadableDatabase();
            String[] argsel = {lector.toString()};
            Log.i("AlimentacionActivity:","::::::::::::::::::::::::::::::::::::::");
            Log.i("AlimentacionActivity:  "+argsel,"------------------------------");
            Log.i("AlimentacionActivity:","::::::::::::::::::::::::::::::::::::::");
            String[] projection = {BasedeDatos.ComidasTabla.COLUMNA_NOMBRES,
                    BasedeDatos.ComidasTabla.COLUMNA_CALORIAS,
                    BasedeDatos.ComidasTabla.COLUMNA_PROTEINAS,
                    BasedeDatos.ComidasTabla.COLUMNA_CARBOHIDRATOS,
                    BasedeDatos.ComidasTabla.COLUMNA_GRASAS};
            Cursor c = db.query(BasedeDatos.ComidasTabla.NOMBRE_TABLA, projection, BasedeDatos.ComidasTabla.COLUMNA_ID + "=?", argsel, null, null, null);
            Log.i("AlimentacionActivity:","--------------------------------------------");

            if(c.moveToFirst()){
                tvCalorias.setText(c.getString(0));
            }else{
                Toast.makeText(this,"No se ha registrado el código de barras en la aplicación",Toast.LENGTH_SHORT).show();
            }
        }*/

    }
    private void mostrarlista(){
        db = new BasedeDatos(this);
        Cursor cursor = db.getComidasD();
        item = new ArrayList<String>();
        String title = "";
        int caloriasitem=0;
        if (cursor.moveToFirst()){
            do{
                title = cursor.getString(1);
                caloriasitem = Integer.parseInt(cursor.getString(2));
                caloriassum += caloriasitem;
                //content = cursor.getString(2);
                item.add(title+" ");
            }while (cursor.moveToNext());
        }
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, item);
        lvAlimentacion.setAdapter(adaptador);
    }
}
