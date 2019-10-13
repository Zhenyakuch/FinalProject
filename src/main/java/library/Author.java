package library;

import java.util.Date;
import java.util.Set;

public class Author {

    Integer id;
    String name;
    Set<Book> books;
    java.util.Date birthDate;

    public Author(Integer id, String name, Set<Book> books, Date birthDate) {
        this.id = id;
        this.name = name;
        this.books = books;
        this.birthDate = birthDate;
    }

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void deleteAuthor(Author author) {

    }


    @Override
    public String toString() {
        return "Author[" +
                "id: " + id +
                ", name: " + name +
                ", birthDate: " + birthDate +
                ']';
    }

}
