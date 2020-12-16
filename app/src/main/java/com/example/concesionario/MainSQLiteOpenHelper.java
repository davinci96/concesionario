package com.example.concesionario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MainSQLiteOpenHelper extends SQLiteOpenHelper{

    public MainSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario(idusuario text primary key,nombre text, clave text)");
        db.execSQL("CREATE TABLE auto(placa text primary key,marca text, modelo text,valor text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE usuario");
        {
            onCreate(db);
        }
        db.execSQL("DROP TABLE auto");
        {
            onCreate(db);
        }
    }
}
