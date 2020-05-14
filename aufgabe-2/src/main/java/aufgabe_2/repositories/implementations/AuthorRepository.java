package aufgabe_2.repositories.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import aufgabe_2.models.Author;
import aufgabe_2.repositories.services.AuthorRepositoryService;
import aufgabe_2.repositories.services.JDBCConnectionService;

public class AuthorRepository implements AuthorRepositoryService {

    private JDBCConnectionService connectionService;

    public AuthorRepository(JDBCConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    public Collection<Author> getAll() {
        String query = "SELECT * FROM Autor";
        List<Author> authors = new ArrayList<Author>();
        try {
            Statement statement = connectionService.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Author author = new Author(resultSet.getInt("id"), resultSet.getString("vorname"),
                        resultSet.getString("nachname"));
                authors.add(author);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;

    }

    @Override
    public Author getById(Integer id) {
        Optional<Author> author = this.getAll().stream().filter(a -> a.Id == id).findFirst();
        return (author.isPresent()) ? author.get() : null;
    }

    @Override
    public Author create(Author author) {
        Author lastInsertedAuthor = this.getAll().stream().max(Comparator.comparing(Author::getId))
                .orElseThrow(NoSuchElementException::new);
        String insertString = "INSERT INTO Autor VALUES(?,?,?)";
        int nextId = lastInsertedAuthor.Id+1;
        boolean authorExitsts = (this.getAll().stream().filter(auth -> (auth.Id == author.Id) || 
                (auth.FirstName.equals(author.FirstName) && auth.LastName.equals(author.LastName))).count()) > 0;
        int created = 0;

        if(!authorExitsts){
            try {
                PreparedStatement statement = connectionService.getConnection().prepareStatement(insertString);
                statement.setInt(1, nextId);
                statement.setString(2, author.FirstName);
                statement.setString(3, author.LastName);
                created = statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return (created >0) ? author : null;
    }

    @Override
    public Author update(Author author) {
        String updateString = "UPDATE Autor SET vorname = ?, nachname = ? WHERE id = ?"; 
        int updated = 0;
        if(author.Id > 0){ //
            try {
                PreparedStatement statement = connectionService.getConnection().prepareStatement(updateString);
                statement.setString(1, author.FirstName);
                statement.setString(2, author.LastName);
                statement.setInt(3, author.Id);
                updated = statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       
       
        return (updated >0) ? author : null;
    }

    @Override
    public Author delete(Author author) {
        String deleteString = "DELETE FROM Autor WHERE id = ? AND vorname = ? AND nachname = ?"; 
        int deleted = 0;
        try {
            PreparedStatement statement = connectionService.getConnection().prepareStatement(deleteString);
            statement.setInt(1, author.Id);
            statement.setString(2, author.FirstName);
            statement.setString(3, author.LastName);
            deleted = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (deleted >0) ? author : null;
    }

    public int delete(int id) {
        String deleteString = "DELETE FROM Autor WHERE id = ?"; 
        int deleted = 0;
        try {
            PreparedStatement statement = connectionService.getConnection().prepareStatement(deleteString);
            statement.setInt(1,id);
            deleted = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public List<Author> getAuthorsByFirstName(String firstName) {
        Optional<List<Author>> authors = Optional.of(this.getAll().stream()
                .filter(author -> author.FirstName.equals(firstName)).collect(Collectors.toList()));

        return (authors.isPresent()) ? authors.get() : null;
    }

    @Override
    public List<Author> getAuthorsByLastName(String lastName) {
        Optional<List<Author>> authors = Optional.of(
                this.getAll().stream().filter(author -> author.LastName.equals(lastName)).collect(Collectors.toList()));
        return (authors.isPresent()) ? authors.get() : null;
    }
}
