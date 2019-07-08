package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bottomnavigation.Modelo.MyAdapter;
import com.example.bottomnavigation.Presentador.CrudFragment;
import com.example.bottomnavigation.Presentador.CursosFragment;
import com.example.bottomnavigation.Presentador.PerfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class nuevaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_principal);
        BottomNavigationView bottomNav= findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new PerfilFragment()).commit();
    }
    private  BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        /*case R.layout.clases:
                            selectedFragment=new MyAdapter();*/
                      /*  case R.id.nav_home:
                            selectedFragment = new CrudFragment();
                            break;*/
                        case R.id.nav_perfil:
                            selectedFragment = new PerfilFragment();
                            break;
                        case R.id.nav_cursos:
                            selectedFragment = new CursosFragment();
                            break;


                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
