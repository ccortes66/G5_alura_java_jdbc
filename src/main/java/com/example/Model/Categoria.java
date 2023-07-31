package com.example.Model;


public final class Categoria implements Comparable<Categoria>
{
    private Long id;
    private String nombre;
    private String cambioNombre;

    public Categoria(){}

    public Categoria(Long id, String nombre) 
    {
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria(String nombre)
    {
        this.nombre = nombre;
    }

    public Categoria(String nombre, String cambioNombre) 
    {
        this.nombre = nombre;
        this.cambioNombre = cambioNombre;
    }

    public Long getId() 
    {
        return id;
    }

    public String getNombre() 
    {
        return nombre;
    }
    
    public String getCambioNombre() 
    {
        return cambioNombre;
    }

    @Override
    public String toString() 
    {
        return "Categoria [id=" + id + ", nombre=" + nombre + "]";
    }


    @Override
    public int compareTo(Categoria o)
    {
        return  o.getId().intValue() - this.id.intValue();
    }

    
    

    

    
    

    
}
