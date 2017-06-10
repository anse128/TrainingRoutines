package com.example.sebastian.trainingroutines;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PerfilActivity extends AppCompatActivity {

    Button btnEntrenamiento, btnSeguimiento, btnAlimentacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnEntrenamiento = (Button) findViewById(R.id.btnentrenamiento);
        btnSeguimiento = (Button) findViewById(R.id.btnseguimiento);
        btnAlimentacion = (Button) findViewById(R.id.btnalimentacion);

        btnAlimentacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, AlimentacionActivity.class);
                startActivity(intent);
            }
        });
    }
}
