package com.example.sebastian.trainingroutines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ivPerfil, ivCrearPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPerfil = (ImageView) findViewById(R.id.ivperfil);
        ivCrearPerfil = (ImageView) findViewById(R.id.ivcrearperfil);

        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AlimentacionActivity.class);
                intent.putExtra("PERFIL",1);
                startActivity(intent);
            }
        });

        ivCrearPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CrearPerfilActivity.class);
                intent.putExtra("CREAR PERFIL",1);
                startActivity(intent);
            }
        });
    }
}
