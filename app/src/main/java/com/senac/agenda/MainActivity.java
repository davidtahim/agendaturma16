package com.senac.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.database.sqlite.SQLiteDatabase;

import android.database.Cursor;

import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}