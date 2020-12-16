package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class autos extends AppCompatActivity {

    EditText jetplaca, jetmodelo, jetmarca, jetvalor;
    Button jbtconsultar, jbtadiccionar, jbtmodificar, jbteliminar, jbtlimpiar, jbtregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);

        getSupportActionBar().hide();
        jetplaca=findViewById(R.id.etplaca);
        jetmodelo=findViewById(R.id.etmodelo);
        jetmarca=findViewById(R.id.etmarca);
        jetvalor=findViewById(R.id.etvalor);
        jbtconsultar=findViewById(R.id.btconsultar);
        jbtadiccionar=findViewById(R.id.btadicionar);
        jbtmodificar=findViewById(R.id.btmodificar);
        jbteliminar=findViewById(R.id.bteliminar);
        jbtlimpiar=findViewById(R.id.btlimpiar);
        jbtregresar=findViewById(R.id.btregresar);
    }

    public void guardar(View view){
        MainSQLiteOpenHelper admin=new MainSQLiteOpenHelper(this,"consecionario",null, 1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String placa,modelo,marca,valor;
        placa=jetplaca.getText().toString();
        modelo=jetmodelo.getText().toString();
        marca=jetmarca.getText().toString();
        valor=jetvalor.getText().toString();

        if(placa.isEmpty() || modelo.isEmpty() || marca.isEmpty() || valor.isEmpty())
        {
            Toast.makeText(this,"Todos los datos son necesarios",Toast.LENGTH_LONG).show();
            jetplaca.requestFocus();
        }
        else
        {
                ContentValues dato=new ContentValues();
                dato.put("placa",placa);
                dato.put("marca",marca);
                dato.put("modelo",modelo);
                dato.put("valor",valor);
                long respuesta=db.insert("auto",null,dato);
                if(respuesta >0)
                {
                    Toast.makeText(this,"registro exitoso",Toast.LENGTH_LONG).show();
                    limpiar_campos();
                }
                else
                {
                    Toast.makeText(this,"Error guardando",Toast.LENGTH_LONG).show();
                }


        }
        db.close();
    }

    public void consultar(View view){
        String placa;
        placa=jetplaca.getText().toString();
        if(placa.isEmpty()){
            Toast.makeText(this,"La placa es requerida", Toast.LENGTH_LONG).show();
            jetplaca.requestFocus();
        }else{
            MainSQLiteOpenHelper Admin=new MainSQLiteOpenHelper(this,"consecionario",null,1);
            SQLiteDatabase db=Admin.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from auto where placa='"+placa+"'",null);
            if(fila.moveToFirst()){
                jetmarca.setText(fila.getString(1));
                jetmodelo.setText(fila.getString(2));
                jetvalor.setText(fila.getString(3));
            }else{
                Toast.makeText(this,"el auto no esta registrado", Toast.LENGTH_LONG).show();
            }
            db.close();
        }
    }

    public void modificar(View view){
        MainSQLiteOpenHelper admin=new MainSQLiteOpenHelper(this,"consecionario",null, 1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String placa,modelo,marca,valor;
        placa=jetplaca.getText().toString();
        modelo=jetmodelo.getText().toString();
        marca=jetmarca.getText().toString();
        valor=jetvalor.getText().toString();

        if(placa.isEmpty() || modelo.isEmpty() || marca.isEmpty() || valor.isEmpty())
        {
            Toast.makeText(this,"Todos los datos son necesarios",Toast.LENGTH_LONG).show();
            jetplaca.requestFocus();
        }
        else
        {
            ContentValues dato=new ContentValues();
            dato.put("placa",placa);
            dato.put("marca",marca);
            dato.put("modelo",modelo);
            dato.put("valor",valor);
            long respuesta=db.update("auto",dato,"placa='"+placa+"'",null);
            if(respuesta >0)
            {
                Toast.makeText(this,"registro exitoso",Toast.LENGTH_LONG).show();
                limpiar_campos();
            }
            else
            {
                Toast.makeText(this,"Error guardando",Toast.LENGTH_LONG).show();
            }


        }
        db.close();
    }

    public void eliminar(View view){
        String placa;
        placa=jetplaca.getText().toString();
        if(placa.isEmpty()){
            Toast.makeText(this,"Placa es requerido",Toast.LENGTH_LONG).show();
            jetplaca.requestFocus();
        }else{
            MainSQLiteOpenHelper Admin=new MainSQLiteOpenHelper(this,"consecionario",null,1);
            SQLiteDatabase db=Admin.getWritableDatabase();
            long respuesta=db.delete("auto","placa='"+placa+"'",null);
            if(respuesta>0){
                Toast.makeText(this,"Registro Borrado",Toast.LENGTH_LONG).show();
                limpiar_campos();
            }else{
                Toast.makeText(this,"Error Borrando",Toast.LENGTH_LONG).show();
            }
            db.close();
        }
    }

    public void limpiar_campos(){
        jetplaca.setText("");
        jetmarca.setText("");
        jetmodelo.setText("");
        jetvalor.setText("");
        jetplaca.requestFocus();
    }

    public void limpiar(View view){
        limpiar_campos();
    }

    public void regresar(View view){
        Intent intmain=new Intent(this,MainActivity.class);
        startActivity(intmain);
    }
}
