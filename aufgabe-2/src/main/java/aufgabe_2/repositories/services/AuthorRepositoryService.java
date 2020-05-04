package aufgabe_2.repositories.services;

import java.util.List;

import aufgabe_2.models.Author;

public interface AuthorRepositoryService extends Repository<Author, Integer>{
    
    List<Author> getAuthorsByFirstName(String firstName);
    List<Author> getAuthorsByLastName(String lastName);

}