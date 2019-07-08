package com.example.bottomnavigation.Presentador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.SesionFragment;

public class PerfilFragment extends Fragment {
    public static final String nombre="nombre";
    TextView cajaNombre;
    View vista;
    Button logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.fragment_perfil,container,false);
        logout=vista.findViewById(R.id.logout);

        cajaNombre=(TextView)vista.findViewById(R.id.textViewname);
        String nombre=getActivity().getIntent().getStringExtra("nombre");
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();

            }
        });


        return vista;
    }

    public void cerrarSesion(){
        SharedPreferences mPrefs= PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor=mPrefs.edit();
        editor.clear();
        editor.commit();
        Intent obj=new Intent(getActivity(), SesionFragment.class);
        startActivity(obj);

    }

}
