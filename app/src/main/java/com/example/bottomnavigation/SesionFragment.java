package com.example.bottomnavigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.bottomnavigation.Bean.UserBean;
import com.example.bottomnavigation.Presentador.PerfilFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SesionFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    View vista;
    RequestQueue rq;
    JsonRequest jrq;
    EditText cajaUser, cajaPwd;
    Button btnIngresar;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        vista= inflater.inflate(R.layout.fragment_sesion,container,false);

        cajaUser=(EditText)vista.findViewById(R.id.txtUser);
        cajaPwd=(EditText)vista.findViewById(R.id.txtPwd);
        btnIngresar=(Button)vista.findViewById(R.id.btnSesion);
        rq= Volley.newRequestQueue(getContext());

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });


        return vista;


    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        UserBean user=new UserBean();
        JSONArray jsonArray=response.optJSONArray("datos");
        JSONObject jsonObject=null;
        try{
            jsonObject=jsonArray.getJSONObject(0);
            user.setUser(jsonObject.optString("user"));
            user.setPwd(jsonObject.optString("pwd"));
            user.setNombre(jsonObject.optString("nombre"));
        }catch (JSONException e){
            e.printStackTrace();
        }

        Intent intencion=new Intent(getContext(), PerfilFragment.class);
        intencion.putExtra(PerfilFragment.nombre, user.getNombre());
        startActivity(intencion);
    }
    private void iniciarSesion(){
        String url="http://192.168.1.60:/login/sesion.php?user="+cajaUser.getText().toString()+"&pwd="+cajaPwd.getText().toString();

        jrq=new JsonObjectRequest(Request.Method.GET, url,null,this,this);
        rq.add(jrq);
    }


}
