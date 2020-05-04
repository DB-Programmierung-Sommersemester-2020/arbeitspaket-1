package aufgabe_3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;


public class App {
    public static void main(String[] args) {
        
        Connection connection = new DBConnection().getConnection();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            
            System.out.println("Driver name and and version: " + metaData.getDriverName()+" : "+metaData.getDriverVersion());
            System.out.println("Database name and and version: " + metaData.getDatabaseProductName()+" : "+metaData.getDatabaseProductVersion());
            System.out.println("Butch-Updates support: " + metaData.supportsBatchUpdates());
            System.out.println("Connection isolation - level: " + metaData.getDefaultTransactionIsolation());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
