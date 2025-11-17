package com.example.tickets.Model;

import java.io.Serializable;

public class Ticket implements Serializable {

    private String titulo;
    private String descripcion;
    private String estado;

    public Ticket(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    // Esto es crucial para que el ListView muestre el título
    @Override
    public String toString() {
        return titulo;
    }
}
