package org.example.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private DataSource datasource;

    public ConnectionFactory() {
        var poolDataSource = new ComboPooledDataSource();
        poolDataSource.setJdbcUrl("jdbc:mysql://localhost:3307/hotel?useTimeZone=true&serverTimeZone=UTC");
        poolDataSource.setUser("root");
        poolDataSource.setPassword("root");
        poolDataSource.setMaxPoolSize(10);

        this.datasource = poolDataSource;
    }

    public Connection recuperaConexion() {
        try {
            return this.datasource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
