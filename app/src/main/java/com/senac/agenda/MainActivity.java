package com.senac.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.database.sqlite.SQLiteDatabase;

import android.database.Cursor;

import android.widget.*;

public class MainActivity extends AppCompatActivity {

    public void telaPrincipal(){
        EditText etNome = (EditText) findViewById(R.id.etNomeCad) ;
        EditText etFone = (EditText) findViewById(R.id.etFoneCad) ;
        Button btGrava = (Button)findViewById(R.id.btGravarCad) ;
        Button btConsulta = (Button)findViewById(R.id.btConsultarCad) ;
    }   Button btFecha = (Button)findViewById(R.id.btFechar) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}