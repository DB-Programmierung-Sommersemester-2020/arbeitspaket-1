package aufgabe_2;

import java.sql.*;

public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","");
        System.out.println( "Hello World!" );
    }
}
