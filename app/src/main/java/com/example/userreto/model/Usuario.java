package com.example.userreto.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    String name;
    String descripcion;
    String genero;
    String urlImagen;

    public Usuario(){}

    public Usuario(String name, String descripcion, String genero, String urlImagen) {
        this.name = name;
        this.descripcion = descripcion;
        this.genero = genero;
        this.urlImagen = urlImagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

}
