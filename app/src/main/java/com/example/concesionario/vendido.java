package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class vendido extends AppCompatActivity {
    Button jbtregresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendido);

        getSupportActionBar().hide();
        jbtregresar=findViewById(R.id.btregresar);
    }

    public void regresar(View view){
        Intent intmain= new Intent(this,MainActivity.class);
        startActivity(intmain);
    }
}
