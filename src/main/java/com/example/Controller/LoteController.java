package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

import com.example.Dao.CrudModelable;
import com.example.Dao.LoteDao;
import com.example.Model.Lote;

public class LoteController implements CrudModelable<Lote,Long>
{  
    private LoteDao dao;

    public LoteController()
    {
        dao = new LoteDao();
    }

    @Override
    public List<Lote> listar(Long page) 
    {
        return (page>0) ? dao.listar(page)
                        : new ArrayList<>();  
    }

    @Override
    public Lote buscar(Long codigo) 
    {   
        return dao.buscar(codigo); 
    }

    @Override
    public Long insertar(Lote objeto) 
    {
       if((objeto.getNombre().isEmpty() || objeto.getNombre() == null) ||
          (objeto.getnombreCategoria().isEmpty() || objeto.getnombreCategoria() == null))
        {
            throw new IllegalArgumentException(" Valores no validos ");
        }
        return dao.insertar(objeto);
    }

    @Override
    public Integer modificar(Lote objeto) 
    {   
        if (objeto.getId()<0)
        {
            throw new IllegalArgumentException(" Valores no validos ");
        }
        return dao.modificar(objeto);
    }

    @Override
    public Integer eliminar(Long codigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }
    
}
