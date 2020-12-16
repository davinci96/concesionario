package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


        EditText jetusuario, jetclave;
        Button jbtingresar, jbtregistar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        jetusuario=findViewById(R.id.etusuario);
        jetclave=findViewById(R.id.etclave);
        jbtingresar=findViewById(R.id.btingresar);
        jbtregistar=findViewById(R.id.btregistrar);
    }

    public void ingresar(View view){
        String usuario, clave;
        usuario=jetusuario.getText().toString();
        clave=jetclave.getText().toString();
        if(usuario.isEmpty() || clave.isEmpty())
        {
            Toast.makeText(this,"la clave y el usuario son requeridas", Toast.LENGTH_LONG).show();
        }else{
            MainSQLiteOpenHelper admin=new MainSQLiteOpenHelper(this,"consecionario",null, 1);
            SQLiteDatabase db=admin.getWritableDatabase();
            Cursor fila=db.rawQuery("select * from usuario where idusuario='"+usuario+"'and clave='"+clave+"'",null);
            if(fila.moveToFirst()){
                Intent intingresar=new Intent(this,SaludoActivity.class);
                intingresar.putExtra("datos",usuario);
                startActivity(intingresar);
            }else{
                Toast.makeText(this,"la clave y el usuario son invalidos", Toast.LENGTH_LONG).show();
                jetclave.setText("");
                jetusuario.requestFocus();
            }
            db.close();

        }
    }
    public void resgistrarse(View view){
        Intent intregistrarse=new Intent(this,RegistrarActivity.class);
        startActivity(intregistrarse);
    }

    public void auto(View view){
        Intent intaut= new Intent(this,autos.class);
        startActivity(intaut);
    }

    public void venta(View view){
        Intent intaut= new Intent(this,ventaActivity.class);
        startActivity(intaut);
    }
}
