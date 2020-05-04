package aufgabe_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import aufgabe_3.resources.DBProperties;

public class DBConnection {
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