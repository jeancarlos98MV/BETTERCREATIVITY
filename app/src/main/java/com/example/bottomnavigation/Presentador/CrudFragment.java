package com.example.bottomnavigation.Presentador;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.Modelo.MyAdapter;
import com.example.bottomnavigation.R;

public class CrudFragment extends Fragment {

    CardView newclass,editclass,remclass, irclases;
    View vista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.fragment_crud,container,false);

        newclass=vista.findViewById(R.id.btnNuevaClase);
        editclass=vista.findViewById(R.id.btnEditarClase);
        remclass=vista.findViewById(R.id.btnEliminarClase);
        irclases=vista.findViewById(R.id.irClases);

        irclases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iraClases();

            }
        });


        newclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevaClase();
            }
        });

        editclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarClase();
            }
        });

        remclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elimClase();
            }
        });


        return vista;

    }
    public void nuevaClase(){
        Intent obj=new Intent(getActivity(),crearclase.class);
        startActivity(obj);
    }
    public void editarClase(){
        Intent obj=new Intent(getActivity(),editarclase.class);
        startActivity(obj);
    }
    public void elimClase(){
        Intent obj=new Intent(getActivity(),eliminarclase.class);
        startActivity(obj);
    }
    public void iraClases(){
        Intent obj=new Intent(getActivity(), MyAdapter.class);
        startActivity(obj);
    }

}
