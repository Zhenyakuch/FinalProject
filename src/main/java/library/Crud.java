package library;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public class Crud {

    public static void insertAuthor(Connection connection, String name, String date) {
        String authorDB = "INSERT INTO author (name, birthDate) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(authorDB);
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAuthor(Connection connection, int id) {
        String authorId = "DELETE FROM author WHERE idAuthor=" + id + "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(authorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAuthor(Connection connection, String name, String date, int id) {
        String authorDB = "UPDATE author SET name='" + name + "', birthDate='" + date + "'WHERE idAuthor=" + id + ";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(authorDB);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertBook(Connection connection, String book_name, int author_id, int genre_id, String isbn, String date) {
        String bookDB = "INSERT INTO book (bookName, author_id, genre_id, ISBN, printDate) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(bookDB);
            preparedStatement.setString(1, book_name);
            preparedStatement.setInt(2, author_id);
            preparedStatement.setInt(3, genre_id);
            preparedStatement.setString(4, isbn);
            preparedStatement.setDate(5, Date.valueOf(date));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFromBook(Connection connection, int id) {
        String bookDelete = "DELETE FROM book WHERE idBook=" + id + ";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(bookDelete);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBook(Connection connection, String book_name, int author_id, int genre_id, String isbn, String date, int id) {
        String updateBook = "UPDATE book SET bookName='" + book_name + "', author_id=" + author_id +
                ", genre_id=" + genre_id + ", ISBN=" + isbn + ", printDate='" + date + "' WHERE idBook=" + id + ";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateBook);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateGenre(Connection connection, Genre genre, int id) {
        String updateGenre = "UPDATE genre SET name='" + genre + "' WHERE idGenre=" + id + ";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateGenre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGenre(Connection connection, int id) {
        String deleteGenre = "DELETE from genre WHERE idGenre=" + id + ";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteGenre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Author> readAuthors(Runner connection) {
        List<Author> authors = new LinkedList<Author>();
        String selectAuthors = "SELECT * FROM author;";
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(selectAuthors);
            while (resultSet.next()) {
                Author author = new Author();
                Set<Book> books = new HashSet<>();
                author.setId(resultSet.getInt("idAuthor"));
                author.setName(resultSet.getString("name"));
                author.setBirthDate(resultSet.getDate("birthDate"));
                author.setBooks(books);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static Set<Book> readBooks(Runner connection, List<Author> list) {
        Set<Book> books = new LinkedHashSet<>();
        String selectBooks = "SELECT * FROM book;";
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(selectBooks);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("idBook"));
                book.setAuthor(list.get(resultSet.getInt("author_id") - 1));
                book.setBookName(resultSet.getString("bookName"));
                book.setGenre(Genre.get(resultSet.getString("genre_id")));
                book.setISBN(resultSet.getString("ISBN"));
                book.setPrintDate(resultSet.getDate("printDate"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static Set<Book> bookForAuthor(Runner connection, int authorId) {
        Set<Book> books = new LinkedHashSet<>();
        String bookDB = "select * from book where author_id=" + authorId + ";";
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(bookDB);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("idBook"));
                book.setBookName(resultSet.getString("bookName"));
                book.setGenre(Genre.get(resultSet.getString("genre_id")));
                book.setISBN(resultSet.getString("ISBN"));
                book.setPrintDate(resultSet.getDate("printDate"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
