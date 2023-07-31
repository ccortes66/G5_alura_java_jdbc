package com.example.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Producto;


public final class ProductoDao implements CrudModelable<Producto,String>
{
    private Long skip;
    private Long limit;
    private String sql;
    private List<Producto> myProducto = new ArrayList<>();

    @Override
    public List<Producto> listar(Long page){
        //temas de paginacion
        this.skip = (page-1) * 10;
        this.limit = this.skip + 10;

        try (Connection con = CrudModelable.getDataSource()) 
        {  
           this.sql = new StringBuilder().append("SELECT ")
                                         .append("id,codigo,nombre,descripcion,precio ")
                                         .append("FROM producto ") 
                                         .append("LIMIT ? OFFSET ?")
                                         .toString(); 

           PreparedStatement statement = con.prepareStatement(sql);
           statement.setLong(1, this.limit);
           statement.setLong(2, this.skip );

           ResultSet resultado = statement.executeQuery();

           while(resultado.next())
           {
              this.myProducto.add(new Producto(resultado.getString("codigo"), 
                                               resultado.getString("nombre"),
                                               resultado.getString("descripcion"),
                                               resultado.getBigDecimal("precio")
                                              )
                                 );
           }

           return new ArrayList<>(this.myProducto);
           
        } catch (SQLException ex) { ex.printStackTrace();}

        return new ArrayList<>() ;
    }

    @Override
    public Producto buscar(String codigo) 
    {

        try (Connection con = CrudModelable.getDataSource()  )
        {
           
           this.sql = new StringBuilder().append("SELECT ")
                                         .append("codigo,nombre,descripcion,precio ")
                                         .append("FROM producto ") 
                                         .append("WHERE codigo = ?")
                                         .toString();

            PreparedStatement stmp = con.prepareStatement(sql);
            stmp.setString(1, codigo.trim());

            ResultSet resultado = stmp.executeQuery();
            
            if(resultado.next())
            {  
               return new Producto(resultado.getString("codigo"), 
                                   resultado.getString("nombre"),
                                   resultado.getString("descripcion"),
                                   resultado.getBigDecimal("precio")
                                  ); 
            }
 
        }catch(SQLException ex){ex.printStackTrace();}
         
        return new Producto();
    }

    @Override
    public Long insertar(Producto objeto) 
    {    
         
         try (Connection con = CrudModelable.getDataSource()  )
         {  
            
            this.sql = new StringBuilder().append("INSERT INTO producto ")
                                              .append("(codigo,nombre,descripcion,precio)")
                                              .append(" VALUES ") 
                                              .append("(?,?,?,?)")
                                              .toString(); 

            PreparedStatement statement = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, objeto.getCodigo().trim());
            statement.setString(2, objeto.getNombre());
            statement.setString(3, objeto.getDescripcion());
            statement.setBigDecimal(4, objeto.getPrecio());
            statement.executeUpdate();
            
            ResultSet idGenerado = statement.getGeneratedKeys();
            if(idGenerado.next())
            {
               return  idGenerado.getLong(1);
            }
            
            
         }catch(SQLException ex) {ex.printStackTrace();}
          return -1L; 
    }

    @Override
    public Integer modificar(Producto objeto) 
    {   
         try (Connection con = CrudModelable.getDataSource())
         {  
            this.sql = new StringBuilder().append("UPDATE producto ")
                                          .append("SET nombre = ?, ")
                                          .append("descripcion = ? , precio = ? ") 
                                          .append("WHERE codigo = ?")
                                          .toString(); 

            PreparedStatement statement = con.prepareStatement(sql);

            
            statement.setString(2, objeto.getNombre());
            statement.setString(3, objeto.getDescripcion());
            statement.setBigDecimal(4, objeto.getPrecio());
            statement.setString(4, objeto.getCodigo().trim());

            
            return statement.executeUpdate();

         }catch(SQLException ex) {ex.printStackTrace();}

        return -1;
    }

    @Override
    public Integer eliminar(String codigo) {
         if(this.buscar(codigo.trim()).equals(null))
         {
             throw new NullPointerException("no existe registro");
         }

         
         try (Connection con = CrudModelable.getDataSource())
         {  

            this.sql = new StringBuilder().append("DELETE FROM producto ")
                                          .append("WHERE codigo = ?")
                                          .toString(); 
            
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, codigo.trim());
            
            return statement.executeUpdate();

          }catch(SQLException ex) {ex.printStackTrace();}
         return  -1;
        
    }
    
}
