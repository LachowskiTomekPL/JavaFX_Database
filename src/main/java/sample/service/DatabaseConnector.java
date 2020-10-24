package sample.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/javafx_project?autoReconnect=true&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection == null) throw new SQLException();
            return connection;
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR WITH DATA BASE !!!!!!!!!!!!! CHECK URL | USERNAME | PASSWORD !!!!!!!!!");
            return null;
        }
    }

    private static final String SQL_USER = "CREATE TABLE USER"
            + "("
            + " ID serial,"
            + " FIRSTNAME varchar(100) NOT NULL,"
            + " LASTNAME varchar(100) NOT NULL,"
            + " PRIMARY KEY (ID)"
            + ")";

    private static final String SQL_LAPTOP = "CREATE TABLE LAPTOP"
            + "("
            + " id integer auto_increment primary key,"
            + " PRICE INTEGER NOT NULL,"
            + " NAME varchar(100) NOT NULL,"
            + " user_id INTEGER,"
            + " foreign key (user_id) references LAPTOP (ID)"
            + ")";

    public void createDates() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(SQL_USER);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void createLaptop() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(SQL_LAPTOP);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        DatabaseConnector base = new DatabaseConnector();
//        base.createDates();
        base.createLaptop();
    }


}
