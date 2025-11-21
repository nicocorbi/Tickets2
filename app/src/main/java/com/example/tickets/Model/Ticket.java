package com.example.tickets.Model;

import java.io.Serializable;

public class Ticket implements Serializable {

    private String titulo;
    private String descripcion;
    private EstadoTicket estado;
    private String RecrearBug;

    public Ticket(String titulo, String descripcion, String textrecrearBug) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.RecrearBug = textrecrearBug;
        this.estado = EstadoTicket.NUEVO;
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

    public EstadoTicket getEstado() {
        return estado;
    }
    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return titulo + " (" + estado.toString() + ")";

    }
}
