package com.example;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.example.Controller.CategoriaController;
import com.example.Controller.LoteController;
import com.example.Controller.ProductoController;
import com.example.Dao.ProductoDao;
import com.example.Model.Categoria;
import com.example.Model.Lote;
import com.example.Model.Producto;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {   
        /*
        List<Producto> productos = new ProductoController().listar(4L);
        //Collections.reverse(productos);
        productos.forEach(System.out::println);
        */

        /*System.out.println(new ProductoController().buscar("M"));*/
       
        
        /*System.out.println(new ProductoController().insertar(new Producto("A00100", 
                                                                         "Vaca",
                                                                         "Armario de cocina en madera de pino"
                                                                         ,new BigDecimal(-100)
                                                                         )
                                                            )
                           );
         */
        
        //System.out.println(new ProductoController().modificar("M003", new Producto(null, null, null, null)) );
        /*
        System.out.println(new ProductoController().modificar(new Producto("M003", 
                                                                         "Mesa Asados",
                                                                         "Meson para asados Aluminio"
                                                                         ,new BigDecimal(500)
                                                                         )
                                                            )
                           );
        */
        //System.out.println(new ProductoController().eliminar("M003"));
       
        /*List<Lote> lotes = new LoteController().listar(1L);
        lotes.forEach(System.out::println);*/
        

        /*System.out.println(new LoteController().insertar(new Lote("Moues", "Informática")))*/;
       /* System.out.println(new LoteController().modificar(new Lote(10L, "Mouses", "Informática"))); */
       /*List<Categoria> categorias = new CategoriaController().listar(1L);
       categorias.stream().sorted().forEach(System.out::println);*/
       /* 
       System.out.println(new CategoriaController().buscar(3L));
       System.out.println(new CategoriaController().modificar(new Categoria("Hogares","Hogar")));
       System.out.println(new CategoriaController().eliminar(22L));
       System.out.println(new CategoriaController().insertar(new Categoria(null)));
       */ 

       List<Producto> productos = new CategoriaController().getProductoPorCategoria();
       productos.forEach(Producto::toInfoString);
    }
}
