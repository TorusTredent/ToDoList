package verification;

import repository.Configs;

import java.sql.*;

public class UniqVerification extends Configs {

    public boolean checkNameUniq(String name, String tableName, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT * FROM " + tableName + " WHERE name = ? AND user_id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, userId);
                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            return false;
                        }
                    }
                }
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkListIsEmpty(String tableName, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT * FROM " + tableName + " WHERE user_id = " + userId + "";
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet rs = statement.executeQuery(query)) {
                        if (rs.next()) {
                            return false;
                        }
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkListLoginIsEmpty() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT * FROM users";
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet rs = statement.executeQuery(query)) {
                        if (rs.next()) {
                            return false;
                        }
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkLoginUniq(String login) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT * FROM users WHERE login = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, login);
                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            return false;
                        }
                    }
                }
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkPasswordIsNotExist(String inputPassword, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)){

                String query = "SELECT * FROM users WHERE password = ? AND id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, inputPassword);
                    preparedStatement.setInt(2, userId);
                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            return false;
                        }
                    }
                }
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
