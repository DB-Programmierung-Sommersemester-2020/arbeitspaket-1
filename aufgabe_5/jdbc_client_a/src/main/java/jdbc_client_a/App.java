package jdbc_client_a;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        String selectString = "SELECT * FROM Adresse WHERE id=8";
        Scanner scanner = new Scanner(System.in);


        try(Connection connection = ConnectionManager.getInstance().getConnection()){
          
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
          
            for(int i = 0; i<=1; i++){
                try(Statement statement = connection.createStatement()){
                    System.out.println("Client A -> Transaktion gestartet");
                    scanner.nextLine();
    
                    System.out.println("Client A -> fuert SELECT aus");
                    ResultSet resultSet = statement.executeQuery(selectString);
                    
                    while(resultSet.next()){
                        System.out.println(resultSet.getString("id") + " : " +resultSet.getString("ort"));
                    }
                    resultSet.close();
                    statement.close();
                    connection.commit();
                    System.out.println("Client A -> Transaktion beendet");
                    
                    System.out.println("\nWarte fuer Eingabe...");
                    scanner.nextLine();
                }
                catch(Throwable e){
                    e.printStackTrace();
                    connection.rollback();
                }
            }
            connection.setAutoCommit(true);
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
