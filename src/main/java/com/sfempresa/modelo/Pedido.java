package com.sfempresa.modelo;

public class Pedido {

    private int id;
    private String direccion;
    private String tipo;

    public Pedido(int id, String direccion, String tipo) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public String getDireccion() { return direccion; }
    public String getTipo() { return tipo; }

    @Override
    public String toString() {
        return "Pedido #" + id + " | " + tipo + " | " + direccion;
    }
}
