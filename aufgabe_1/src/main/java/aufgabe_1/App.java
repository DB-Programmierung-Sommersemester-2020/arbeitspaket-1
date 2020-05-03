package aufgabe_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/demodb";
        String query = "SELECT * FROM Verlag";

        try (Connection connection = DriverManager.getConnection(url, "root", "")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("name"));
                    }
                }
            }
            connection.close();
        }
    }
}
