package com.example.bottomnavigation.Presentador;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bottomnavigation.Modelo.uploadPDF;
import com.example.bottomnavigation.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/*
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
*/

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class crearclase extends AppCompatActivity {
    private static final int PICK_FILE = 1 ;
    private DatabaseReference databaseReference;


    EditText editPDFname, nombre, hora, clase, codigo;
    Button btnUplodPDF, crear, cancel;
/*
    StorageReference storageReference;
    DatabaseReference databaseReference;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearclase);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("NuevoPDF");

        /*btnUplodPDF = (Button) findViewById(R.id.btnSubirpdf);*/
/*
        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        nombre=(EditText)findViewById(R.id.txtNombre);
        hora=(EditText)findViewById(R.id.txtHoraclase);
        clase=(EditText)findViewById(R.id.txtSalon);
        crear=(Button)findViewById(R.id.btnCrear);
        cancel=(Button)findViewById(R.id.btnCancelar);
        codigo=(EditText)findViewById(R.id.txtID);
*/


/*
        btnUplodPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDFFile();

            }
        });
*/

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirDatos("http://192.168.1.60:/clases/insertar_clase.php");

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backClass();

            }
        });

    }

        //Subir PDF

/*
    public void selectPDFFile(){
        Intent obj=new Intent();
        obj.setType("application/pdf");
        obj.setAction(obj.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(obj, "Selecciona un PDF"),1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            uploadPDFFile(data.getData());
        }
    }
*/

/*    private void uploadPDFFile(Uri data) {
        final ProgressDialog barra=new ProgressDialog(this);
        barra.setTitle("Cargando...");
        barra.show();

        StorageReference storage= storageReference.child("uploads/"+ System.currentTimeMillis()+"pdf");
        storage.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uri= taskSnapshot.getStorage().getDownloadUrl();
                        while (!uri.isComplete());
                        Uri url=uri.getResult();
                        uploadPDF uploadPDF=new uploadPDF(editPDFname.getText().toString(),url.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
                        Toast.makeText(crearclase.this, "Archivo Subido", Toast.LENGTH_SHORT).show();
                        barra.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress=(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                barra.setMessage("Se subió: " +(int)progress+"%");

            }
        });
    }*/


        //Subir datos a Mysql
    public void subirDatos(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "DATOS SUBIDOS!!", Toast.LENGTH_SHORT).show();
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
                paraemtros.put("nombre",nombre.getText().toString());
                paraemtros.put("salon",clase.getText().toString());
                paraemtros.put("hora",hora.getText().toString());
                return paraemtros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




    public void FileUpload(View view) {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,PICK_FILE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        final ProgressDialog barra=new ProgressDialog(this);
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == PICK_FILE){

            if(resultCode == RESULT_OK){


                Uri FileUri = data.getData();

                StorageReference Folder = FirebaseStorage.getInstance().getReference().child("Files");


                final StorageReference file_name = Folder.child("file"+FileUri.getLastPathSegment());



                file_name.putFile(FileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        file_name.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("filelink", String.valueOf(uri));


                                databaseReference.setValue(hashMap);
                                Toast.makeText(crearclase.this, "File Uploaded", Toast.LENGTH_SHORT).show();


                            }


                        });





                    }

                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress=(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                        barra.setMessage("Se subió: " +(int)progress+"%");

                    }
                });



            }




        }




    }

    public void backClass(){
        Intent back=new Intent(crearclase.this,CrudFragment.class);
        startActivity(back);

    }

}
