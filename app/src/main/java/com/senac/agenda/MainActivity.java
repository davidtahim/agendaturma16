package com.senac.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.database.sqlite.SQLiteDatabase;

import android.database.Cursor;

import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

SQLiteDatabase db = null;
Cursor cursor;

public void abreCriabd () {
    try {
        db = openOrCreateDatabase("banco",MODE_PRIVATE,null);
    } catch (Exception erro) {
        msg("Erro ao criar ou abrir o banco de dados");
    }
    try {
        db.execSQL("CREATE TABLE IF NOT EXISTS contatos (id INTEGER PRIMARY KEY, nome TEXT, fone TEXT);");

    } catch (Exception erro) {
        msg("Erro ao criar a tabela");
    }
    // finally {
    //    msg("Tabela criada com sucesso");
    //}

}

public  void fechadb(){
    db.close();
}

public void insereRegistro(){
    try {
        abreCriabd();
        EditText etNome = (EditText) findViewById(R.id.etNomeCad) ;
        EditText etFone = (EditText) findViewById(R.id.etFoneCad) ;
        db.execSQL("INSERT INTO contatos (nome, fone) VALUES ('" + etNome.getText().toString() + "','" + etFone.getText().toString()+ "')");
        msg("Registro Inserido");
        etNome.setText(null);
        etFone.setText(null);
        etNome.requestFocus();
        fechadb();
    } catch (Exception erro) {
        msg("Erro ao inserir no banco de dados");

    }
}

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
                insereRegistro();
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
                android.os.Process.killProcess(android.os.Process.myPid());
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