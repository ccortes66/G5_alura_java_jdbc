package com.example.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.example.Datasource.HirakiCpConfig;
public interface CrudModelable<T,E>
{
    
    List<T> listar(Long page);
    T buscar(E codigo);
    Long insertar(T objeto);
    Integer modificar(T objeto);
    Integer eliminar(E codigo);
    
    static Connection getDataSource() throws SQLException
    {
        return HirakiCpConfig.getDataSource().getConnection();
    }  
   
}
