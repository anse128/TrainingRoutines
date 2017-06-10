package com.example.sebastian.trainingroutines;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CrearPerfilActivity extends AppCompatActivity {


    EditText etPeso, etEdad, etAltura;

    int Peso = 0,
        Edad = 0,
        Altura = 0, Objetivo;
    double  Proteinas=0, Carbohidratos=0, Grasas=0;
    int Calorias=0;

    double actividad ;

    Button btnRegistrar;
    BasedeDatos db;
    private double Sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_perfil);

        etPeso = (EditText) findViewById(R.id.etpeso);
        etEdad = (EditText) findViewById(R.id.etedad);
        etAltura = (EditText) findViewById(R.id.etaltura);
        btnRegistrar = (Button) findViewById(R.id.btnregistrar);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.actividad_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(view.getId()) {
                    case 0:
                        actividad =  1.2;
                        break;
                    case 1:
                        actividad =  1.375;
                        break;
                    case 2:
                        actividad =  1.55;
                        break;
                    case 3:
                        actividad =  1.77;
                        break;
                    case 4:
                        actividad =  1.9;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.sexo_array, android.R.layout.simple_spinner_item);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(view.getId()) {
                    case 0:
                        Sexo =  1;
                        break;
                    case 1:
                        Sexo =  2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.objetivo_array, android.R.layout.simple_spinner_item);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner3.setAdapter(adapter3);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(view.getId()) {
                    case 0:
                        Objetivo =  0;
                        break;
                    case 1:
                        Objetivo =  1;
                        break;
                    case 2:
                        Objetivo =  2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDatos();
            }
        });

    }


    private void addDatos() {

        //Modo = .getText().toString();
        boolean etpeso_vacio = etPeso.getText().toString().isEmpty();
        boolean etedad_vacio = etEdad.getText().toString().isEmpty();
        boolean etaltura_vacio = etAltura.getText().toString().isEmpty();

        if (etpeso_vacio || etedad_vacio || etaltura_vacio) {

            Toast.makeText(this, "No se han ingresado todos los datos", Toast.LENGTH_SHORT).show();

        } else {

            Peso = Integer.parseInt(etPeso.getText().toString());
            Edad = Integer.parseInt(etEdad.getText().toString());
            Altura = Integer.parseInt(etAltura.getText().toString());

            if (Sexo == 1) {
                Calorias = (int) (66 + (13.7 * Peso) + (5 * Altura) - (6.8 * Edad) * actividad);
            } else {
                Calorias = (int) (655 + (9.6 * Peso) + (1.8 * Altura) - (4.7 * Edad) * actividad);
            }

            switch (Objetivo) {
                case 0:
                    Calorias = (int) (Calorias + Calorias * 0.2);
                    break;
                case 1:
                    Calorias = (int) (Calorias - Calorias * 0.2);
                    break;
                case 2:
                    break;
            }/*
            final BasedeDatos basedeDatos = new BasedeDatos(getApplicationContext());
            SQLiteDatabase db = basedeDatos.getWritableDatabase();
            ContentValues valores = new ContentValues();

            valores.put(basedeDatos.COLUMNA_CALORIAS_P, Calorias);
            valores.put(basedeDatos.COLUMNA_PROTEINAS_P, "");
            valores.put(basedeDatos.COLUMNA_CARBOHIDRATOS_P, "");
            valores.put(basedeDatos.COLUMNA_GRASAS_P, "");

            String[] argsel = {"1"};
            String Selection = basedeDatos.COLUMNA_ID_P+"=?";

            db.update(basedeDatos.NOMBRE_TABLA2,valores,Selection,argsel);*/

            db = new BasedeDatos(this);
            db.addPerfil("1",String.valueOf(Calorias), "", "", "");
            this.finish();
            Intent intent = new Intent(CrearPerfilActivity.this, AlimentacionActivity.class);
            startActivity(intent);

        }
    }
}