package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ventaActivity extends AppCompatActivity {

    TextView jtvmodelo, jtvmarca, jtvprecio;
    EditText jetplaca;
    Button jbtconsultar, jbtvender, jbtregresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        getSupportActionBar().hide();
        jtvmodelo=findViewById(R.id.tvmodelo);
        jtvmarca=findViewById(R.id.tvmarca);
        jtvprecio=findViewById(R.id.tvprecio);
        jetplaca=findViewById(R.id.etplaca);
        jbtconsultar=findViewById(R.id.btconsultar);
        jbtvender=findViewById(R.id.btvender);
        jbtregresar=findViewById(R.id.btregresar);

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
                jtvmarca.setText(fila.getString(1));
                jtvmodelo.setText(fila.getString(2));
                jtvprecio.setText(fila.getString(3));
            }else{
                Toast.makeText(this,"el auto no esta registrado", Toast.LENGTH_LONG).show();
            }
            db.close();
        }
    }

    public void regresar(View view){
        Intent intmain= new Intent(this,MainActivity.class);
        startActivity(intmain);
    }

    public void vender(View view){
        Intent intmain= new Intent(this,vendido.class);
        startActivity(intmain);
    }
}
