package aufgabe_2.app;

import java.sql.*;

import aufgabe_2.models.Author;
import aufgabe_2.repositories.implementations.AuthorRepository;
import aufgabe_2.repositories.implementations.DatabaseConection;
import aufgabe_2.resources.DBProperties;

public class App {

    public static void main(String[] args) throws SQLException {

        AuthorRepository repository = new AuthorRepository(new DatabaseConection());

        System.out.println(repository.getById(1).FirstName);
        repository.getAuthorsByFirstName("Scott").forEach(System.out::println);
        repository.getAuthorsByLastName("Edwards").forEach(System.out::println);
        repository.getAll().forEach(System.out::println);
    }

}
