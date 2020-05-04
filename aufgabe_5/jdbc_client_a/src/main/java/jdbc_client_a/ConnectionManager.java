package jdbc_client_a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jdbc_client_b.ressources.DBProperties;

public class ConnectionManager {
    
  private static ConnectionManager instance;
  
  private ConnectionManager () {}
  
  public static ConnectionManager getInstance () {
    if (ConnectionManager.instance == null) {
      ConnectionManager.instance = new ConnectionManager ();
    }
    return ConnectionManager.instance;
  }

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