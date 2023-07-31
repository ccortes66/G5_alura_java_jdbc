package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

import com.example.Dao.CrudModelable;
import com.example.Dao.ProductoDao;
import com.example.Model.Producto;

public final class ProductoController implements CrudModelable<Producto,String>
{
    private ProductoDao dao;

    public ProductoController()
    {
        this.dao = new ProductoDao();
    }

    @Override
    public List<Producto> listar(Long page) 
    {
        return (page>0) ? dao.listar(page)
                        : new ArrayList<>();  
    }

    @Override
    public Producto buscar(String codigo) 
    {
        if (codigo.isEmpty() || codigo == null)
        {
            throw new NullPointerException("campo codigo esta vacio");
        }

        return dao.buscar(codigo.trim());

    }

    @Override
    public Long insertar(Producto objeto) {
    
        if( (objeto.getCodigo() == null || objeto.getCodigo().isEmpty()) ||
            (objeto.getNombre() == null || objeto.getNombre().isEmpty()) ||
            (objeto.getDescripcion() == null || objeto.getDescripcion() .isEmpty()) ||
            (objeto.getPrecio() == null || objeto.getPrecio().floatValue()<=0)
        ){ throw new IllegalArgumentException(" Valores no validos ");}
        
        return dao.insertar(objeto);
    }

    @Override
    public Integer modificar(Producto objeto) 
    {
        return dao.modificar(objeto);
    }

    @Override
    public Integer eliminar(String codigo) 
    {
        return dao.eliminar(codigo.trim());
    }
    
}
