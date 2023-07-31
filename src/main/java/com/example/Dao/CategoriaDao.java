package com.example.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Categoria;
import com.example.Model.Producto;

public final class CategoriaDao implements CrudModelable<Categoria,Long>
{
    private List<Categoria> myCategorias = new ArrayList<>(); 
    private List<Producto> myProductos = new ArrayList<>();
    private Long skip;
    private Long limit;
    private String sql;


    private CategoriaDao(){}

    private static CategoriaDao dao;

    public static CategoriaDao getCategoriaDao()
    {
        return(dao == null) ? dao = new CategoriaDao()
                            : dao;
    }

    @Override
    public List<Categoria> listar(Long page) 
    {   
        this.skip = (page - 1) * 10;
        this.limit = this.skip + 10;

        try (Connection con = CrudModelable.getDataSource()) 
        {
            this.sql = new StringBuilder()
                                        .append("SELECT id,nombre FROM categoria ")
                                        .append("LIMIT ? OFFSET ?") 
                                        .toString();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, limit);
            statement.setLong(2, skip);
            ResultSet resultado  = statement.executeQuery();
            while(resultado.next())
            {
               myCategorias.add(new Categoria(resultado.getLong("id"),
                                              resultado.getString("nombre")));
            }

            return new ArrayList<>(myCategorias);
        } catch (SQLException ex) {ex.printStackTrace();}

        return new ArrayList<>();
    }

    @Override
    public Categoria buscar(Long codigo) 
    {
        
        try(Connection conn = CrudModelable.getDataSource())
        {
           this.sql = new StringBuilder()
                                       .append("SELECT id, nombre ")
                                       .append("FROM categoria ")
                                       .append("WHERE id = ?") 
                                       .toString();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, codigo);
            
            ResultSet resultado = statement.executeQuery();
            if(resultado.next())
            {
                return new Categoria(resultado.getLong("id"),
                                     resultado.getString("nombre"));
            }
        }catch(SQLException ex){ex.printStackTrace();}
        return new Categoria();
    }

    @Override
    public Long insertar(Categoria objeto) 
    {
        
        try (Connection conn = CrudModelable.getDataSource()) 
        {
            this.sql = new StringBuilder()
                                        .append("INSERT INTO categoria (nombre) VALUES (?)")  
                                        .toString();
            
            PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, objeto.getNombre().trim());
            statement.executeQuery();

            ResultSet resultado = statement.getGeneratedKeys();
            if(resultado.next())
            {
                return resultado.getLong(1);
            }

        } catch (SQLException ex) {ex.printStackTrace();} 
        return -1L;
    }

    @Override
    public Integer modificar(Categoria objeto) 
    {
        try (Connection con = CrudModelable.getDataSource()) 
        {
            this.sql = new StringBuilder()
                                       .append("UPDATE categoria ")
                                       .append("SET nombre = ? ")
                                       .append("WHERE nombre = ?")
                                       .toString();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, objeto.getCambioNombre());
            statement.setString(2, objeto.getNombre());
            Integer resultado  = statement.executeUpdate();
            if(resultado>0)
            {
                return resultado;
            }
        } catch (SQLException ex) {ex.printStackTrace();}
        return -1;
    }

    @Override
    public Integer eliminar(Long codigo) 
    {
         try (Connection conn = CrudModelable.getDataSource()) 
         {  
            this.sql = "DELETE FROM categoria WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, codigo);
            Integer resultado  = statement.executeUpdate();
            if(resultado>0)
            {
                return resultado;
            } 
         } catch (SQLException ex) {ex.printStackTrace();}    

         return -1;
    }

    public List<Producto> getProductoPorCategoria()
    {   
       try (Connection connection = CrudModelable.getDataSource()) 
       {
           this.sql = new StringBuilder() 
                            .append("SELECT p.codigo,p.nombre,p.precio,")
                            .append("pl.cantidad,(SELECT nombre ")
                                          .append("FROM categoria c ")
                                          .append("WHERE id = l.categoria_id")
                                          .append(") AS categoria ")
                            .append("FROM producto_lote pl ")
                            .append("INNER JOIN producto p ") 
                            .append("ON p.codigo  = pl.codigo_producto ") 
                            .append("INNER JOIN lote l ") 
                            .append("ON l.id = pl.lote_id ")
                            .toString();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while(resultado.next())
        {
            myProductos.add(new Producto(resultado.getString("p.codigo"),
                                         resultado.getString("p.nombre"),
                                         resultado.getBigDecimal("p.precio"),
                                         resultado.getInt("pl.cantidad"),
                                         resultado.getString("categoria")
                                        ));
        }

        return new ArrayList<>(myProductos);

       } catch (SQLException ex) {ex.printStackTrace();}
       return new ArrayList<>();
    }
    
}
