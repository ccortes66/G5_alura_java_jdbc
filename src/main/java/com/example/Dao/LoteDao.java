package com.example.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Lote;

public final class LoteDao implements CrudModelable<Lote,Long>{
     
    private String sql;
    private Long skip;
    private Long limit;
    private String sqlNombreCategoria = "(SELECT nombre FROM categoria WHERE id = categoria_id ) As nombre_categoria ";
    private List<Lote> myLotes = new ArrayList<>();
    
    @Override
    public List<Lote> listar(Long page) {
        
        try (Connection con = CrudModelable.getDataSource()  )
         {  
            this.skip = (page-1) * 10;
            this.limit = skip + 10;

            this.sql = new StringBuilder().append("SELECT ")
                                          .append("id,nombre, ")
                                          .append(this.sqlNombreCategoria)
                                          .append("FROM lote ") 
                                          .append("LIMIT ? OFFSET ?")
                                          .toString();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, limit);
            statement.setLong(2, skip);
            ResultSet resultado = statement.executeQuery();

            while(resultado.next())
            {
                myLotes.add(new Lote(resultado.getLong("id"), 
                                     resultado.getString("nombre"),
                                     resultado.getString("nombre_categoria")));
            }

            return new ArrayList<>(myLotes);
            
         }catch(SQLException ex) {ex.printStackTrace();}
          return new ArrayList<>(); 
    }

    @Override
    public Lote buscar(Long codigo) {
        
        try (Connection con = CrudModelable.getDataSource()) 
        {   
            this.sql = new StringBuilder().append("SELECT ")
                                          .append("id,nombre, ")
                                          .append(this.sqlNombreCategoria)
                                          .append("FROM lote ") 
                                          .append("WHERE id = ?")
                                          .toString();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, codigo);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next())
            {
                return new Lote(resultado.getLong("id"), 
                                resultado.getString("nombre"),
                                resultado.getString("nombre_categoria"));
            }


        } catch (SQLException ex) {ex.printStackTrace();}

        return new Lote();
    }

    @Override
    public Long insertar(Lote objeto) {
        
        try (Connection con = CrudModelable.getDataSource()) 
        {  
           this.sql = new StringBuilder().append("INSERT INTO lote(nombre,categoria_id) ")
                                         .append("VALUES( ?, ")
                                         .append("(SELECT id FROM categoria WHERE nombre = ? ))")
                                         .toString(); 
           PreparedStatement statement = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);   
           statement.setString(1, objeto.getNombre());
           statement.setString(2, objeto.getnombreCategoria());
           statement.executeQuery();

           ResultSet idGenerado = statement.getGeneratedKeys();
           if(idGenerado.next())
           {   
              
              return idGenerado.getLong(1);
           }
        } catch (SQLException ex) {ex.printStackTrace();}

        return -1L;
    }

    @Override
    public Integer modificar(Lote objeto) 
    {
        
       try (Connection con = CrudModelable.getDataSource()) 
       {
          this.sql = new StringBuilder()
                                       .append("UPDATE lote ")
                                       .append("SET nombre= ?, ")
                                       .append("categoria_id = (SELECT id FROM categoria WHERE nombre = ? )")
                                       .append("WHERE id = ?")
                                       .toString();
        
           PreparedStatement statement = con.prepareStatement(sql);
           statement.setString(1, objeto.getNombre().trim());
           statement.setString(2, objeto.getnombreCategoria().trim());  
           statement.setLong(3, objeto.getId());  

           return statement.executeUpdate();
        
        
       } catch (SQLException ex) {ex.printStackTrace();}

       return -1;
    }

    @Override
    public Integer eliminar(Long codigo) {
        
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }
    
}
