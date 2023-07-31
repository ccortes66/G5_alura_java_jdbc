package com.example.Datasource;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;

public final class HirakiCpConfig 
{
    private HirakiCpConfig(){}

    private static HikariDataSource dataSource;

    public static HikariDataSource getDataSource()
    {
        if(dataSource == null)
        {   
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mariadb://localhost:3306/control_de_stock");
            config.setUsername("criss");
            config.setPassword( "1234");
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(5);
            dataSource = new HikariDataSource(config) ;
        }

        return dataSource;
    }
}
