package server.dataBase;

import common.bookComponents.Book;
import common.bookComponents.Colors;
import common.bookComponents.Janrs;
import common.bookComponents.Point;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class DataBaseUsersController {
    private boolean flag;

    DataBaseUsersController() {}

    public void readUsers(){
        try (Connection connection = DataLog.getConnection()){
            if((connection != null) && (!connection.isClosed())){
                System.out.println("Connection Active");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from users;");

                while (resultSet.next()){
                    String userLogin = resultSet.getString(2);
                    String userPassword = resultSet.getString(3);

                    DataLog.addNewUser(userLogin, userPassword);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveNewUser(String userLogin, String userPassword){
        try (Connection connection = DataLog.getConnection()){
            if((connection != null) && (!connection.isClosed())){
                System.out.println("Connection Active");
                Statement statement = connection.createStatement();
                statement.execute("insert into users(login, password) " +
                        "values('" + userLogin + "', '" + userPassword + "');");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataLog.addNewUser(userLogin, userPassword);
    }
}
