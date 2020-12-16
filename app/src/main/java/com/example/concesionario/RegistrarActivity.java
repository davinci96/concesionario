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

public class RegistrarActivity extends AppCompatActivity {

    EditText jetusuario, jetnombre, jetclave1, jetclave2;
    Button jbtconsultar, jbtadiccionar, jbtmodificar, jbteliminar, jbtlimpiar, jbtregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        getSupportActionBar().hide();
        jetusuario=findViewById(R.id.etusuario);
        jetnombre=findViewById(R.id.etnombre);
        jetclave1=findViewById(R.id.etclave1);
        jetclave2=findViewById(R.id.etclave2);
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
        String usuario,nombre,clave1,clave2;
        usuario=jetusuario.getText().toString();
        nombre=jetnombre.getText().toString();
        clave1=jetclave1.getText().toString();
        clave2=jetclave2.getText().toString();

        if(usuario.isEmpty() || nombre.isEmpty() || clave1.isEmpty() || clave2.isEmpty())
        {
            Toast.makeText(this,"Todos los datos son necesarios",Toast.LENGTH_LONG).show();
            jetusuario.requestFocus();
        }
        else
        {
            if(!clave1.equals(clave2))
            {
                Toast.makeText(this,"Las claves no son iguales",Toast.LENGTH_LONG).show();
                jetclave1.requestFocus();
            }
            else
            {
                ContentValues dato=new ContentValues();
                dato.put("idusuario",usuario);
                dato.put("nombre",nombre);
                dato.put("clave",clave1);
                long respuesta=db.insert("usuario",null,dato);
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
        }
        db.close();
    }

    public void consultar(View view){

        String usuario;
        usuario=jetusuario.getText().toString();
        if(usuario.isEmpty()){
            Toast.makeText(this,"el usuario es requerido", Toast.LENGTH_LONG).show();
            jetusuario.requestFocus();
        }
        else{
            MainSQLiteOpenHelper Admin=new MainSQLiteOpenHelper(this,"consecionario",null,1);
            SQLiteDatabase db=Admin.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from usuario where idusuario='"+usuario+"'",null);
            if(fila.moveToFirst()){
                jetnombre.setText(fila.getString(1));
                jetclave1.setText(fila.getString(2));
            }else{
                Toast.makeText(this,"el usuario no esta registrado", Toast.LENGTH_LONG).show();
            }
            db.close();
        }
    }

    public void modificar(View view){
        String usuario,nombre,clave1,clave2;
        usuario=jetusuario.getText().toString();
        nombre=jetnombre.getText().toString();
        clave1=jetclave1.getText().toString();
        clave2=jetclave2.getText().toString();

        if(usuario.isEmpty() || nombre.isEmpty() || clave1.isEmpty() || clave2.isEmpty())
        {
            Toast.makeText(this,"Todos los datos son necesarios",Toast.LENGTH_LONG).show();
            jetusuario.requestFocus();
        }else{
            if(!clave1.equals(clave2)){
                Toast.makeText(this,"Las claves no son iguales",Toast.LENGTH_LONG).show();
                jetclave1.requestFocus();
            }else{


            MainSQLiteOpenHelper Admin=new MainSQLiteOpenHelper(this,"consecionario",null,1);
            SQLiteDatabase db=Admin.getWritableDatabase();
            ContentValues dato=new ContentValues();
            dato.put("idusuario",usuario);
            dato.put("nombre",nombre);
            dato.put("clave",clave1);
            long respuesta=db.update("usuario",dato,"idusuario='"+usuario+"'",null);
            if(respuesta>0){
                Toast.makeText(this,"Registro modificado",Toast.LENGTH_LONG).show();
                limpiar_campos();
            }else{
                Toast.makeText(this,"Error modificando",Toast.LENGTH_LONG).show();
            }
            db.close();
            }
        }

    }

    public void eliminar(View view){
        String usuario;
        usuario=jetusuario.getText().toString();
        if(usuario.isEmpty()){
            Toast.makeText(this,"Usuario es requerido",Toast.LENGTH_LONG).show();
            jetusuario.requestFocus();
        }else{
            MainSQLiteOpenHelper Admin=new MainSQLiteOpenHelper(this,"consecionario",null,1);
            SQLiteDatabase db=Admin.getWritableDatabase();
            long respuesta=db.delete("usuario","idusuario='"+usuario+"'",null);
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
        jetusuario.setText("");
        jetnombre.setText("");
        jetclave1.setText("");
        jetclave2.setText("");
        jetusuario.requestFocus();
    }

    public void limpiar(View view){
        jetusuario.setText("");
        jetnombre.setText("");
        jetclave1.setText("");
        jetclave2.setText("");
        jetusuario.requestFocus();
    }

    public void regresar(View view){
        Intent intmain=new Intent(this,MainActivity.class);
        startActivity(intmain);
    }
}
