package com.example.tickets.Model;

public class Ticket {

    private int id;

    private String title;
    private String descripcion;
    private String recreacionBug;
    private String estado;
    public void ticket(String title, String recreacionBug, String descripcion, String estado){
        this.title = title;
        this.recreacionBug = recreacionBug;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getTitle(){
        return title;
    }
    public String getRecreacionBug(){
        return recreacionBug;
    }
    public String getDescripcion(){
        return descripcion;
    }
}
