package aufgabe_2.repositories.implementations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public Author create(Author entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Author update(Author entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Author delete(Author entity) {
        // TODO Auto-generated method stub
        return null;
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