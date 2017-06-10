package com.example.sebastian.trainingroutines;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class LectorCodigosActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    int opcion;
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);// Set the scanner view as the content view
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            opcion = extras.getInt("AÑADIR");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("Scaner:", rawResult.getContents()); // Prints scan results
        Log.v("Hola", rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        String codigo = rawResult.getContents();

        switch (opcion) {
            case 1:
                final BasedeDatos basedeDatos = new BasedeDatos(getApplicationContext());


                SQLiteDatabase db = basedeDatos.getReadableDatabase();
                String[] argsel = {codigo};
                String[] projection = {BasedeDatos.COLUMNA_NOMBRES, BasedeDatos.COLUMNA_CALORIAS,
                                        BasedeDatos.COLUMNA_PROTEINAS, BasedeDatos.COLUMNA_CARBOHIDRATOS,
                                        BasedeDatos.COLUMNA_GRASAS};
                Cursor c = db.query(BasedeDatos.NOMBRE_TABLA, projection, BasedeDatos.COLUMNA_ID + "=?", argsel, null, null, null);
                c.moveToFirst();
                    Log.i("AddDatos",":::::::::::::::::::::::::::::::::::::::::::");
                if (c.moveToFirst()) {
                    String nombre = c.getString(0),
                            calorias = c.getString(1),
                            proteinas = c.getString(2),
                            carbohidratos = c.getString(3),
                            grasas = c.getString(4);
                    //basedeDatos.addComidas(nombre,calorias,"","","","");
                    Intent intent = new Intent(LectorCodigosActivity.this, AlimentoDetalleActivity.class);
                    intent.putExtra("NOMBRE", nombre);
                    intent.putExtra("CALORIAS", calorias);
                    intent.putExtra("PROTEINAS", proteinas);
                    intent.putExtra("CARBOHIDRATOS", carbohidratos);
                    intent.putExtra("GRASAS", grasas);
                    this.finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Este codigo no se encuentra en la base de datos", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(LectorCodigosActivity.this, ComidasActivity.class);
                    this.finish();
                    startActivity(intent2);
                }
                break;
            case 2:
                Intent intent2 = new Intent(LectorCodigosActivity.this, AgregarAlimentoActivity.class);
                intent2.putExtra("CODIGO",codigo);
                this.finish();
                startActivity(intent2);
                break;
        }


        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}