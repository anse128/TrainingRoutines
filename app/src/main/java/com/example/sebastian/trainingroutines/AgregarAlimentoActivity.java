package com.example.sebastian.trainingroutines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarAlimentoActivity extends AppCompatActivity {
    EditText etNombre, etCalorias, etProteinas, etCarbohidratos, etGrasas;
    Button btnAgregar;
    String Nombre;
    String Calorias;
    String Proteinas;
    String Carbohidratos;
    String Grasas;
    String Id = null;
    BasedeDatos db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alimento);

        btnAgregar = (Button) findViewById(R.id.btnagregar);
        etNombre = (EditText) findViewById(R.id.etnombre);
        etCalorias = (EditText) findViewById(R.id.etcalorias);
        etProteinas = (EditText) findViewById(R.id.etproteinas);
        etCarbohidratos = (EditText) findViewById(R.id.etcarbohidratos);
        etGrasas = (EditText) findViewById(R.id.etgrasas);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDatos();
            }
        });

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Id = extras.getString("CODIGO");
        }
    }

    private void addDatos(){
        Nombre = etNombre.getText().toString();
        Calorias = etCalorias.getText().toString();
        Proteinas = etProteinas.getText().toString();
        Carbohidratos = etCarbohidratos.getText().toString();
        Grasas = etGrasas.getText().toString();
        db = new BasedeDatos(this);
        db.addComidas(Id, Nombre, Calorias, Proteinas, Carbohidratos, Grasas);
        //db.close();
        Intent intent = new Intent(AgregarAlimentoActivity.this, ComidasActivity.class);
        startActivity(intent);
    }
}
