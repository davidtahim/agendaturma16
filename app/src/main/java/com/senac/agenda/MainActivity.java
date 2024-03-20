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

    public void abreCriabd() {
        try {
            db = openOrCreateDatabase("banco", MODE_PRIVATE, null);
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

    public void fechadb() {
        db.close();
    }

    public void insereRegistro() {
        try {
            abreCriabd();
            EditText etNome = (EditText) findViewById(R.id.etNomeCad);
            EditText etFone = (EditText) findViewById(R.id.etFoneCad);
            db.execSQL("INSERT INTO contatos (nome, fone) VALUES ('" + etNome.getText().toString() + "','" + etFone.getText().toString() + "')");
            msg("Registro Inserido");
            etNome.setText(null);
            etFone.setText(null);
            etNome.requestFocus();
            fechadb();
        } catch (Exception erro) {
            msg("Erro ao inserir no banco de dados");

        }
    }

    public void mostrarDados() {
        TextView etNome = (TextView) findViewById(R.id.tvNomeCon);
        TextView etFone = (TextView) findViewById(R.id.tvFoneCon);

        etNome.setText(cursor.getString(cursor.getColumnIndex("nome")));
        etFone.setText(cursor.getString(cursor.getColumnIndex("fone")));
    }

    public boolean buscarDados() {
        try {
            abreCriabd();
            cursor = db.query("contatos", new String[]{"nome", "fone"},
                    null, null, null, null, null, null
            );
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                mostrarDados();
                return true;
            } else {
                msg("Nenhum registro");
                return false;
            }
        } catch(Exception erro){
                msg("Erro ao buscar registros");
                return false;
            }

    }

    public void excluirRegistro() {
        try {
            abreCriabd();
            db.execSQL("DELETE FROM contatatos WHERE id =" + cursor.getInt(cursor.getColumnIndex("id")));
            msg("Registro excluído");
            cursor = db.rawQuery("SELECT * FROM contatos", null);
            mostrarDados();
            fechadb();
        } catch (Exception erro) {
            msg("Erro ao excluir o registro");
        }
    }

    public void editarRegistro() {
        try {
        abreCriabd();
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String nome = cursor.getString(cursor.getColumnIndex("nome"));
        String fone = cursor.getString(cursor.getColumnIndex("fone"));
        fechadb();
        } catch (Exception erro) {
            msg("Erro ao editar registro");
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
        Button btExcluir = (Button) findViewById(R.id.btExcluirCon);
        Button btEditar = (Button) findViewById(R.id.btEditarCon);
        buscarDados();
    btAnterior.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // ir para o anterior
            antReg();
        }
    });

    btProximo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // ir para o proximo
            proxReg();
        }
    });

    btVoltar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // voltar para o início
            fechadb();
            telaPrincipal();
        }
    });
    btExcluir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //excluir registro
            excluirRegistro();
        }
    });
    btEditar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editarRegistro();
        }
    });
    }
    public void proxReg() {
        try {
            cursor.moveToNext();
            mostrarDados();
        } catch (Exception erro) {
            if (cursor.isAfterLast()) {
                msg("Não há mais registros");
            } else {
                msg("Erro ao navegar");
            }
        }
    }

    public void antReg() {
        try {
            cursor.moveToPrevious();
            mostrarDados();
        } catch (Exception erro) {
            if (cursor.isBeforeFirst()) {
                msg("Não há registros anteriores");
            } else {
                msg("Erro ao navegar");
            }
        }
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