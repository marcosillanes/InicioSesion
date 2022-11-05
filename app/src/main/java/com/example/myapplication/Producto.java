package com.example.myapplication;

public class Producto  {
    private Long Costo;
    private String Descripcion;
    private String Nombre_Producto;

    public Producto(Long costo, String descripcion, String nombre) {
        this.Costo = costo;
        this.Descripcion = descripcion;
        this.Nombre_Producto = nombre;
    }
    public Producto() {

    }

    public Long getCosto() {
        return Costo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "Costo=" + Costo +
                ", Descripcion='" + Descripcion + '\'' +
                ", Nombre_Producto='" + Nombre_Producto + '\'' +
                '}';
    }

    public void setCosto(Long costo) {
        this.Costo = costo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String nombre_Producto) {
        this.Nombre_Producto = nombre_Producto;
    }
}