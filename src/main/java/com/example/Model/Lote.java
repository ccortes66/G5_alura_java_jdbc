package com.example.Model;

public final class Lote
{
    private Long id;
    private String nombre;
    private String nombreCategoria;

    public Lote() {}

    public Lote(Long id, String nombre, String nombreCategoria) 
    {
        this.id = id;
        this.nombre = nombre;
        this.nombreCategoria = nombreCategoria;
    }

    public Lote(String nombre, String nombreCategoria) 
    {
        this.nombre = nombre;
        this.nombreCategoria = nombreCategoria;
    }

    

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getnombreCategoria() {
        return nombreCategoria;
    }

    @Override
    public String toString() 
    {
        return "Lote [id=" + id + ", nombre=" + nombre + ", categoria=" + nombreCategoria + "]";
    }

    

    

    
}
