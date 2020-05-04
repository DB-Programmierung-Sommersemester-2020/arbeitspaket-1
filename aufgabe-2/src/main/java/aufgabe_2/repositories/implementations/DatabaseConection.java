package aufgabe_2.repositories.implementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import aufgabe_2.repositories.services.JDBCConnectionService;
import aufgabe_2.resources.DBProperties;

public class DatabaseConection implements JDBCConnectionService {

    @Override
    public Connection getConnection() {
        
        final DBProperties properties = new DBProperties();
        final String url = properties.URL + ":" + properties.PORT + "/" + properties.DATABASE;
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url, properties.USER_NAME, properties.USER_PASSWORD);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return connection;

    }
    
}