package repository;

import entity.User;

import java.sql.*;

public class UserRepository extends Configs{

    public static void createUser(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "INSERT INTO users (login, password) values (?, ?)";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setString(1, user.getLogin());
                    prepStatement.setString(2, user.getPassword());
                    prepStatement.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showLogin(int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT login FROM users WHERE id = " + userId + "";

                try (Statement statement = connection.createStatement()) {
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                    }
                    rs.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showPassword(int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT password FROM users WHERE id = " + userId + "";

                try (Statement statement = connection.createStatement()) {
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                    }
                    rs.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateLogin(String newLogin, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "UPDATE users SET login = ? WHERE id = ?";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setString(1, newLogin);
                    prepStatement.setInt(2, userId);
                    prepStatement.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePassword(String newPassword, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "UPDATE users SET password = ? WHERE id = ?";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setString(1, newPassword);
                    prepStatement.setInt(2, userId);
                    prepStatement.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int returnIdUser (String login) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT * FROM users WHERE login = ?";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setString(1, login);
                    ResultSet result = prepStatement.executeQuery();
                    if (result.next()) {
                        return result.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
