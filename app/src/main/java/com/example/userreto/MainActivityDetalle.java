package com.example.userreto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.userreto.model.Usuario;
import com.squareup.picasso.Picasso;

public class MainActivityDetalle extends AppCompatActivity {

    TextView TextViewName, TextViewDescripcion, TextViewGenero;
    ImageView imagenview;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detalle);

        TextViewName = findViewById(R.id.TextViewName);
        TextViewDescripcion = findViewById(R.id.TextViewDescripcion);
        TextViewGenero = findViewById(R.id.TextViewGenero);
        imagenview = findViewById(R.id.ImagenUrl);

        cargarData();

    }

    public void cargarData(){
        Intent inputText = getIntent();
        usuario = (Usuario) inputText.getSerializableExtra("Usuario");
        TextViewName.setText(usuario.getName());
        TextViewDescripcion.setText(usuario.getDescripcion());
        TextViewGenero.setText(usuario.getGenero());
        Picasso.with(this).load(usuario.getUrlImagen()).into(imagenview);
    }
}