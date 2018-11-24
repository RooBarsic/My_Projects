package server.dataBase;

import common.bookComponents.Book;
import com.google.gson.Gson;
import common.bookComponents.Colors;
import common.bookComponents.Janrs;
import common.bookComponents.Point;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBaseBookController {
    private boolean flag;

    DataBaseBookController() {}

    public void readBooks(){
        try (Connection connection = DataLog.getConnection()){
            if((connection != null) && (!connection.isClosed())){
                System.out.println("Connection Active");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from books;");

                while (resultSet.next()){
                    String bookName = resultSet.getString(2);
                    String bookJanr = resultSet.getString(3);
                    int xBookCoordinate = resultSet.getInt(4);
                    int yBookCoordinate = resultSet.getInt(5);
                    String bookColor = resultSet.getString(6);

                    Book book = new Book(bookName, Janrs.valueOf(bookJanr),
                            new Point(xBookCoordinate, yBookCoordinate),
                            Colors.valueOf(bookColor));
                    DataLog.addBook(book);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveBooks(){
        try (Connection connection = DataLog.getConnection()){
            if((connection != null) && (!connection.isClosed())){
                System.out.println("Connection Active");
                Statement statement = connection.createStatement();
                statement.execute("drop table books;");
                statement.execute("CREATE TABLE Books (\n" +
                                "  id          INTEGER PRIMARY KEY,\n" +
                                "  BookName    varchar(30),\n" +
                                "  BookJanr    varchar(30),\n" +
                                "  Xcoordinate integer,\n" +
                                "  Ycoordinate integer,\n" +
                                "  BookColor   varchar(30)\n" +
                                ");");
                List<Book> books = DataLog.getBooks();

                for(int i = 0; i < books.size(); i++){
                    Book book = books.get(i);
                    statement.addBatch("insert into Books(id, bookname, bookjanr, xcoordinate, ycoordinate, bookcolor)\n" +
                            "values ("+ Integer.toString(i) + ", '" + book.getName() + "' , '" + book.getJanr().name() + "', " + Integer.toString(book.getBookCoordinates().getX()) +
                            ", " + Integer.toString(book.getBookCoordinates().getY()) + ", '" + book.getBookColor().name() + "');");
                }
                statement.executeBatch();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
