package aufgabe_2.models;

public class Author {
    public int Id;
    public String FirstName;
    public String LastName;

    public Author() {
    }

    public Author(int id, String firstName, String lastName) {
        this.Id = id;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    // TODO override Hash, toString ect.

    @Override
    public String toString() {
        return this.Id + " : " + this.FirstName + " : " + this.LastName;
    }
}