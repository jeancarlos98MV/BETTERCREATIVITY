package com.example.bottomnavigation.Presentador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bottomnavigation.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class buscarclase extends AppCompatActivity {
    EditText nombre,hora,clase,codigo;
    Button buscar,cancel;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscarclase);
        buscar=(Button)findViewById(R.id.btnBuscar);
        cancel=(Button)findViewById(R.id.btnCancelar);
        codigo=(EditText)findViewById(R.id.txtID);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarDatos("http://192.168.1.60:/clase/buscar_clase.php?codigo"+codigo.getText()+"");

            }
        });
    }
    private void buscarDatos(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nombre.setText(jsonObject.getString("nombre"));
                        clase.setText(jsonObject.getString("clase"));
                        hora.setText(jsonObject.getString("hora"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
