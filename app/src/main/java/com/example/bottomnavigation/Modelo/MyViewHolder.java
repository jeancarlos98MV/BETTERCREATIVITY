package com.example.bottomnavigation.Modelo;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView mName, mLink;
    Button mDownload;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        mName=itemView.findViewById(R.id.name);
        mLink=itemView.findViewById(R.id.link);
        mDownload=itemView.findViewById(R.id.download);

    }


}
