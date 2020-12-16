package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SaludoActivity extends AppCompatActivity {

    TextView jtvusruario;
    Button jbtregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);

        getSupportActionBar().hide();
        jtvusruario=findViewById(R.id.tvusuario);
        jbtregresar=findViewById(R.id.btregresar);
        String usuario;
        usuario=getIntent().getStringExtra("datos");
        jtvusruario.setText(usuario);
    }

    public void regresar(View view){
        Intent intmain= new Intent(this,MainActivity.class);
        startActivity(intmain);
    }
}
