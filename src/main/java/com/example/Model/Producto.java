package com.example.Model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public final class Producto 
{
    private String codigo;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer cantidad;
    private String categoria;
    
    public Producto(){}

    public Producto(String codigo, 
                    String nombre, 
                    String descripcion, 
                    BigDecimal precio) 
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    public Producto(String codigo, 
                    String nombre, 
                    BigDecimal precio,
                    Integer cantidad,
                    String categoria)
    {   
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio="
                + precio + "]";
    }

    public void toInfoString(){
        System.out.printf("Producto [codigo=%s, nombre=%s, precio=%s , cantidad=%d, categoria=%s]%n"
                             ,codigo
                             ,nombre
                             ,new DecimalFormat("#,###.00").format(precio)
                             ,cantidad
                             ,categoria 
                            );
    }

   

    

    

    


     
}
