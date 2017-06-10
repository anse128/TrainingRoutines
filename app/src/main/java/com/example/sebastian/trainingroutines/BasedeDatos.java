package com.example.sebastian.trainingroutines;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by sebastian on 09/05/17.
 */

public class BasedeDatos extends SQLiteOpenHelper {
    public BasedeDatos(Context context) { //, String name, SQLiteDatabase.CursorFactory factory, int version
        super(context, DATABASE_NAME, null, DATABASE_VERSION ); //, name, factory, version);
    }
    Cursor cursor;

    public static final String NOMBRE_TABLA = "Comidas";
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_NOMBRES = "Nombres";
    public static final String COLUMNA_CALORIAS = "Calorias";
    public static final String COLUMNA_PROTEINAS = "Proteinas";
    public static final String COLUMNA_CARBOHIDRATOS = "Carbohidratos";
    public static final String COLUMNA_GRASAS = "Grasas";

    private static final String CREAR_TABLA_1 =
            "CREATE TABLE " + NOMBRE_TABLA + " (" +
                    COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    COLUMNA_NOMBRES + " TEXT," +
                    COLUMNA_CALORIAS + " TEXT," +
                    COLUMNA_PROTEINAS + " TEXT," +
                    COLUMNA_CARBOHIDRATOS + " TEXT," +
                    COLUMNA_GRASAS + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NOMBRE_TABLA;

    public static final String NOMBRE_TABLA2 = "Perfil";
    public static final String COLUMNA_ID_P = "id";
    public static final String COLUMNA_CALORIAS_P = "Calorias_totales";
    public static final String COLUMNA_PROTEINAS_P = "Proteinas";
    public static final String COLUMNA_CARBOHIDRATOS_P = "Carbohidratos";
    public static final String COLUMNA_GRASAS_P = "Grasas";

    private static final String CREAR_TABLA_2 =
            "CREATE TABLE " + NOMBRE_TABLA2 + " (" +
                    COLUMNA_ID_P + " INTEGER," +
                    COLUMNA_CALORIAS_P + " INTEGER, " +
                    COLUMNA_PROTEINAS_P + " TEXT," +
                    COLUMNA_CARBOHIDRATOS_P + " TEXT," +
                    COLUMNA_GRASAS_P + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_2 =
            "DROP TABLE IF EXISTS " + NOMBRE_TABLA2;

    public static final String NOMBRE_TABLA_3 = "Comidadia";
    public static final String COLUMNA_ID_D = "id";
    public static final String COLUMNA_NOMBRES_D = "Nombres";
    public static final String COLUMNA_CALORIAS_D = "Calorias";
    public static final String COLUMNA_PROTEINAS_D = "Proteinas";
    public static final String COLUMNA_CARBOHIDRATOS_D = "Carbohidratos";
    public static final String COLUMNA_GRASAS_D = "Grasas";

    private static final String CREAR_TABLA_3 =
            "CREATE TABLE " + NOMBRE_TABLA_3 + " (" +
                    COLUMNA_ID_D + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMNA_NOMBRES_D + " TEXT," +
                    COLUMNA_CALORIAS_D + " TEXT," +
                    COLUMNA_PROTEINAS_D + " TEXT," +
                    COLUMNA_CARBOHIDRATOS_D + " TEXT," +
                    COLUMNA_GRASAS_D + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_3 =
            "DROP TABLE IF EXISTS " + NOMBRE_TABLA_3;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BasedeDatos.db";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_1);
        db.execSQL(CREAR_TABLA_2);
        db.execSQL(CREAR_TABLA_3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_2);
        db.execSQL(SQL_DELETE_ENTRIES_3);
        onCreate(db);
    }

    public void addComidas(String id, String nombre, String calorias, String proteinas, String carbohidratos, String grasas) {
        ContentValues valores = new ContentValues();
        valores.put(COLUMNA_ID,id);
        valores.put(COLUMNA_NOMBRES,nombre);
        valores.put(COLUMNA_CALORIAS,calorias);
        valores.put(COLUMNA_PROTEINAS,proteinas);
        valores.put(COLUMNA_CARBOHIDRATOS,carbohidratos);
        valores.put(COLUMNA_GRASAS,grasas);
        this.getWritableDatabase().insert(NOMBRE_TABLA,null,valores);
    }

    public Cursor getComidas(){
        String columnas[] = {COLUMNA_ID,COLUMNA_NOMBRES,COLUMNA_CALORIAS,
                COLUMNA_PROTEINAS,COLUMNA_CARBOHIDRATOS,COLUMNA_GRASAS};
        cursor = this.getReadableDatabase().query(NOMBRE_TABLA, columnas, null, null,
                null, null, null, null);
        return cursor;
    }


    public void addComidasD(String nombre, String calorias, String proteinas, String carbohidratos, String grasas) {
        ContentValues valores = new ContentValues();
        valores.put(COLUMNA_NOMBRES_D,nombre);
        valores.put(COLUMNA_CALORIAS_D,calorias);
        valores.put(COLUMNA_PROTEINAS_D,proteinas);
        valores.put(COLUMNA_CARBOHIDRATOS_D,carbohidratos);
        valores.put(COLUMNA_GRASAS_D,grasas);
        this.getWritableDatabase().insert(NOMBRE_TABLA_3,null,valores);
    }

    public Cursor getComidasD(){
        String columnas[] = {COLUMNA_ID_D, COLUMNA_NOMBRES_D, COLUMNA_CALORIAS_D, COLUMNA_PROTEINAS_D,
                COLUMNA_CARBOHIDRATOS_D, COLUMNA_GRASAS_D};
        cursor  = this.getReadableDatabase().query(NOMBRE_TABLA_3, columnas, null, null,
                null, null, null, null);
        return cursor;
    }


    public void addPerfil(String id, String calorias, String edad, String altura, String modo) {
        ContentValues valores = new ContentValues();
        valores.put(COLUMNA_ID_P,id);
        valores.put(COLUMNA_CALORIAS_P,calorias);
        valores.put(COLUMNA_PROTEINAS_P,edad);
        valores.put(COLUMNA_CARBOHIDRATOS_P,altura);
        valores.put(COLUMNA_GRASAS_P,modo);
        this.getWritableDatabase().insert(NOMBRE_TABLA2,null,valores);
    }


    public long getId(){

        Cursor cursor = getComidas();
        long i=0;
        if (cursor.moveToFirst()){
            do{
                i = Long.parseLong(cursor.getString(0));
                //content = cursor.getString(2);
            }while (cursor.moveToNext());
        }
        return i;
    }
}
