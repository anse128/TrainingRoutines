package com.example.sebastian.trainingroutines;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlimentoDetalleActivity extends AppCompatActivity {

    TextView tvNombre, tvCalorias, tvProteinas, tvCarbohidratos, tvGrasas;
    Button button;
    String NOMBRE, CALORIAS, PROTEINAS, CARBOHIDRATOS, GRASAS;

    BasedeDatos db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimento_detalle);

        tvNombre = (TextView) findViewById(R.id.tvnombredetalle);
        tvCalorias = (TextView) findViewById(R.id.tvcaloriasdetalle);
        tvProteinas = (TextView) findViewById(R.id.tvproteinasdetalle);
        tvCarbohidratos = (TextView) findViewById(R.id.tvcarbohidratosdetalle);
        tvGrasas = (TextView) findViewById(R.id.tvgrasasdetalle);
        button = (Button) findViewById(R.id.btnagregardetalle);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {;
            NOMBRE = extras.getString("NOMBRE");
            CALORIAS = extras.getString("CALORIAS");
            PROTEINAS = extras.getString("PROTEINAS");
            CARBOHIDRATOS = extras.getString("CARBOHIDRATOS");
            GRASAS = extras.getString("GRASAS");
        }

        tvNombre.setText("Nombre: " + NOMBRE);
        tvCalorias.setText("Calorias: " + CALORIAS);
        tvProteinas.setText("Proteinas: " + PROTEINAS);
        tvCarbohidratos.setText("Carbohidratos: " + CARBOHIDRATOS);
        tvGrasas.setText("Grasas: " + GRASAS);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComidasdelDia();
                Intent intent = new Intent(AlimentoDetalleActivity.this, ComidasActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addComidasdelDia(){
        db = new BasedeDatos(this);
        db.addComidasD(NOMBRE, CALORIAS, PROTEINAS, CARBOHIDRATOS, GRASAS);
        //db.close();
        Intent intent = new Intent(AlimentoDetalleActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
