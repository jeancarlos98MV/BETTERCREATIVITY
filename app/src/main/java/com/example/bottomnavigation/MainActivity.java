package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bottomnavigation.Presentador.CrudFragment;
import com.example.bottomnavigation.Presentador.CursosFragment;
import com.example.bottomnavigation.Presentador.PerfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);*/


        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.iniciarSesion,new SesionFragment()).commit();

    }






}
