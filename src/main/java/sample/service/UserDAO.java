package sample.service;

import sample.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try
                (
                        Connection connection = DatabaseConnector.connect();
                        PreparedStatement ps = connection.prepareStatement("SELECT * FROM user")
                ) {

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID");
                String firstName = resultSet.getString("FIRSTNAME");
                String lastname = resultSet.getString("LASTNAME");

                userList.add(new User(id, firstName, lastname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static void deleteUser(User user) {
        try
                (
                        Connection connection = DatabaseConnector.connect();
                        PreparedStatement ps = connection.prepareStatement("delete from user where id = ?")
                ) {

            ps.setInt(1, user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String firstName, String lastName) {
        try
                (
                        Connection connection = DatabaseConnector.connect();
                        PreparedStatement ps = connection.prepareStatement("insert into user values(null, ?,?)")
                ) {

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
