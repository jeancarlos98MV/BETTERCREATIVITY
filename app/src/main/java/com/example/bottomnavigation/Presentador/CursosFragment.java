package com.example.bottomnavigation.Presentador;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.Modelo.MyAdapter;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.Presentador.crearclase;

public class CursosFragment extends Fragment {
    CardView mate,histo,comu;
    View vista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.fragment_cursos,container,false);
        mate=vista.findViewById(R.id.idMatematica);
        histo=vista.findViewById(R.id.idHistoria);
        comu=vista.findViewById(R.id.idComuni);

        comu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irCOmu();
            }
        });

        histo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irHIsto();
            }
        });


        mate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irMAte();
            }
        });
        return vista;



    }
    public void irMAte(){
        Intent obj=new Intent(getContext(), MyAdapter.class);
        startActivity(obj);

    }
    public void irHIsto(){
        Intent obj1=new Intent(getContext(),MyAdapter.class);
        startActivity(obj1);
    }
    public void irCOmu(){
        Intent obj2=new Intent(getContext(),MyAdapter.class);
        startActivity(obj2);
    }




}
