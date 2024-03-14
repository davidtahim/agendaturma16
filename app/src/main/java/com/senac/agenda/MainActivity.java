package com.senac.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.database.sqlite.SQLiteDatabase;

import android.database.Cursor;

import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    public void telaPrincipal() {
        setContentView(R.layout.activity_main);
        EditText etNome = (EditText) findViewById(R.id.etNomeCad);
        EditText etFone = (EditText) findViewById(R.id.etFoneCad);
        Button btGrava = (Button) findViewById(R.id.btGravarCad);
        Button btConsulta = (Button) findViewById(R.id.btConsultarCad);
        Button btFechar = (Button) findViewById(R.id.btFechar);

        btGrava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gravação
            }
        });

        btConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //consulta
                telaConsulta();
            }
        });

        btFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fechar
            }
        });
    }
        public void telaConsulta(){
        setContentView(R.layout.consulta);
        TextView tvNome = (TextView) findViewById(R.id.tvNomeCon) ;
        TextView tvFone = (TextView) findViewById(R.id.tvFoneCon) ;
        Button btAnterior = (Button) findViewById(R.id.btAnteCon) ;
        Button btProximo = (Button) findViewById(R.id.btProxCon) ;
        Button btVoltar = (Button) findViewById(R.id.btVoltarCon) ;

    btAnterior.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // ir para o anterior
        }
    });

    btProximo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // ir para o proximo
        }
    });

    btVoltar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // voltar para o início
            telaPrincipal();
        }
    });

    }

    public void msg(String txt){
        AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
        adb.setMessage(txt);
        adb.setNeutralButton("OK",null);
        adb.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        telaPrincipal();
    }
}