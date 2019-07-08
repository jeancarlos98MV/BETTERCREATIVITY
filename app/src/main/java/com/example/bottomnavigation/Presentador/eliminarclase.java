package com.example.bottomnavigation.Presentador;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bottomnavigation.R;

import java.util.HashMap;
import java.util.Map;

public class eliminarclase extends AppCompatActivity {
    EditText codigo,nombre,clase,hora;
    Button eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminarclase);
        codigo=(EditText)findViewById(R.id.txtID);
        nombre=(EditText)findViewById(R.id.txtNombre);
        hora=(EditText)findViewById(R.id.txtHoraclase);
        clase=(EditText)findViewById(R.id.txtSalon);
        eliminar=(Button)findViewById(R.id.btnEliminar);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarDatos("http://192.168.1.60:/clase/eliminar_clase.php");
            }
        });
    }

    public void eliminarDatos(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "DATOS ELIMINADOS!!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> paraemtros=new HashMap<String, String>();
                paraemtros.put("codigo",codigo.getText().toString());
                return paraemtros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
