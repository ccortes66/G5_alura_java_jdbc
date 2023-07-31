package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

import com.example.Dao.CategoriaDao;
import com.example.Dao.CrudModelable;
import com.example.Model.Categoria;
import com.example.Model.Producto;

public class CategoriaController implements CrudModelable<Categoria,Long>
{   
    private CategoriaDao dao;

    public CategoriaController()
    {
        dao = CategoriaDao.getCategoriaDao();

    }

    @Override
    public List<Categoria> listar(Long page) 
    {   
        System.out.println(page);
        return (page<=0) ? new ArrayList<>()
                         : dao.listar(page);
    }

    @Override
    public Categoria buscar(Long codigo) {
        if(codigo<=0){throw new IllegalArgumentException(" Valor no valido ");}
        return dao.buscar(codigo);
    }

    @Override
    public Long insertar(Categoria objeto) 
    {  
        if((objeto.getNombre().isEmpty() || objeto.getNombre() == null))
          {throw new IllegalArgumentException(" Valor no valido ");} 
       return dao.insertar(objeto);
    }

    @Override
    public Integer modificar(Categoria objeto) {
        if((objeto.getNombre().isEmpty() || objeto.getNombre() == null) ||
            objeto.getCambioNombre().isEmpty() || objeto.getNombre() == null)
            {throw new IllegalArgumentException(" Valor no valido ");}   
        return dao.modificar(objeto); 
    }

    @Override
    public Integer eliminar(Long codigo) {
        if(codigo<=0){throw new IllegalArgumentException(" Valor no valido ");}
        return dao.eliminar(codigo);
    }

    public List<Producto> getProductoPorCategoria()
    {
        return dao.getProductoPorCategoria();
    }

    
    
}
