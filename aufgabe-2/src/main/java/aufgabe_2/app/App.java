package aufgabe_2.app;

import java.sql.*;

import aufgabe_2.models.Author;
import aufgabe_2.repositories.implementations.AuthorRepository;
import aufgabe_2.repositories.implementations.DatabaseConection;


public class App {

    public static void main(String[] args) throws SQLException {

        /*Aufgabe ist etwas anders geloest. Fuer den Fall mit sql - injection sind die Kommentare 
        an den passenden Stellen in AuthorRepository eingefuegt, fuer die Ausgabe von Authors nach Nachname bzw. Vorname sind die entsprechenden
        Methoden eingefuegt , jedoch mit dem Einsatz von Collections und Streams...*/

        AuthorRepository repository = new AuthorRepository(new DatabaseConection());

        Author autor = new Author();
        autor.setFirstName("Robert");
        autor.setLastName("Martin");

        repository.create(autor);
        System.out.println("------------------------Created---------------------------------");
        repository.getAuthorsByFirstName("Robert").forEach(System.out::println);

        autor.setFirstName("Robert C.");
        autor.setId(27);
        repository.update(autor);
        System.out.println("------------------------Ubdated---------------------------------");
        repository.getAuthorsByFirstName("Robert C.").forEach(System.out::println);
        
        
        repository.delete(autor);
        repository.getAuthorsByFirstName("Robert");

        System.out.println(repository.getById(1).FirstName);     
        repository.getAuthorsByLastName("Edwards").forEach(System.out::println);
        
        System.out.println("---------------------------------All-Authors---------------------------------");
        repository.getAll().forEach(System.out::println);
    }

}
