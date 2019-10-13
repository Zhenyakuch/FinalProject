package library;

import library.Author;
import library.Book;

import java.sql.*;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Runner {
    public static Connection connection;
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE_URL = "jdbc:mysql://localhost:3306/books_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "Jeka1234";

    public Connection getConnection() {
        return connection;
    }

    static {
        System.out.println("ВАЖНО! Дата задается в таком формате yyyy-mm-dd");
    }

    public Runner() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Runner runner = new Runner();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите опрецию ктоорую хотите произвести:  " +
                "1. Вывести данные из таблицы   " +
                "2. Внести данные в таблицу   " +
                "3. удалить данные из таблицы ");
        int number = scanner.nextInt();

        switch (number){
            case 1:
                System.out.println("Из какой таблицы данные вы хотите получить?  " +
                        "1. Автора  " +
                        "2. Книг  " );
                int c = scanner.nextInt();
                switch (c){
                    case 1:
                        System.out.println(Crud.readAuthors(runner));
                        break;
                    case 2:
                        System.out.println("Введите id автора");
                        int authorId = scanner.nextInt();
                        System.out.println(Crud.bookForAuthor(runner,authorId ));
                        break;
                    case 3:
                        break;
                }
                break;
            case 2:
                System.out.println("В какую таблицу хотите внести данные  " +
                        "1. Автора  " +
                        "2. Книг  " );
                int k = scanner.nextInt();
                switch (k){
                    case 1:
                        System.out.println("Введите имя и дату");
                        String name = scanner.next();
                        String date = scanner.next();
                        Crud.insertAuthor((Connection) connection, name, date);
                        System.out.println("Данные внесены :)");
                        break;
                    case 2:
                        System.out.println("Введите имя книги,  idАвтора, idЖанра, ISBN и дату");
                        String book_name = scanner.next();
                        int author_id = scanner.nextInt();
                        int genre_id = scanner.nextInt();
                        String isbn = scanner.next();
                        String date2 = scanner.next();
                        Crud.insertBook((Connection) connection,  book_name,  author_id,  genre_id,  isbn, date2);
                        System.out.println("Данные внесены :)");
                        break;
                }
                break;
            case 3:
                System.out.println("Из какой таблицы вы хотите удалить данные  " +
                        "1. Автора  " +
                        "2. Книг " +
                        "3. Жанров " );
                int del = scanner.nextInt();
                switch (del){
                    case 1:
                        System.out.println("Введите id автора для удаления");
                        int id = scanner.nextInt();
                        Crud.deleteAuthor((Connection) connection, id);
                        break;
                    case 2:
                        System.out.println("Введите id книги для удаления");
                        int idb = scanner.nextInt();
                        Crud.deleteFromBook((Connection) connection, idb);
                        break;
                    case 3:
                        System.out.println("Введите id жанра для удаления");
                        int idg = scanner.nextInt();
                        Crud.deleteGenre((Connection) connection, idg);
                }
                break;
        }


        runner.closeConnection();
    }


}
