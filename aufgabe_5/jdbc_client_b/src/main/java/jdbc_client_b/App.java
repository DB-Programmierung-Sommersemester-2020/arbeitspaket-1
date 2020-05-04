package jdbc_client_b;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        String updateString = "UPDATE Adresse SET ort='Berlin' WHERE ort='Hamburg'";

        try(Connection connection = ConnectionManager.getInstance().getConnection()){
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            try(Statement statement = connection.createStatement()){
                System.out.println("Client B -> Transaktion gestartet");
                scanner.nextLine();

                System.out.println("Client B -> fuert UPDATE aus");
                int count = statement.executeUpdate(updateString);

                System.out.println("Aenderungen "+count);

                System.out.println("Client B -> Warte nach Update auf Commit");
                scanner.nextLine();
               
                connection.commit();
            }
            catch(Throwable e){
                e.printStackTrace();
                connection.rollback();
            }
            finally{
                connection.setAutoCommit(true);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    
    
    
    }
}
