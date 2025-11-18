package com.example.tickets.Model;

import java.io.Serializable;

public class Ticket implements Serializable {

    private String titulo;
    private String descripcion;
    private String estado;
    private String RecrearBug;

    public Ticket(String titulo, String descripcion, String textrecrearBug) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.RecrearBug = textrecrearBug;
        this.estado = "Abierto";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRecrearBug(){return RecrearBug;}

    public void setRecrearBug(String RecrearBug){this.RecrearBug = RecrearBug;}
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
