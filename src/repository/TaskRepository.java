package repository;


import entity.Task;

import java.sql.*;


public class TaskRepository extends Configs {

    public static void addTask(Task task, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "INSERT INTO tasks (name, description, status, category_id, user_id) values (?, ?, ?, ?, ?)";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setString(1, task.getName());
                    prepStatement.setString(2, task.getDescription());
                    prepStatement.setString(3, task.getStatus());
                    prepStatement.setInt(4, task.getCategoryId());
                    prepStatement.setInt(5, userId);
                    prepStatement.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTask(String inputName, Task task, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "UPDATE tasks SET name = ?, description = ?, status = ?, category_id = ? " +
                        "WHERE user_id = ? AND name = ?";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setString(1, task.getName());
                    prepStatement.setString(2, task.getDescription());
                    prepStatement.setString(3, task.getStatus());
                    prepStatement.setInt(4, task.getCategoryId());
                    prepStatement.setInt(5, userId);
                    prepStatement.setString(6, inputName);
                    prepStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTaskStatus(String inputName, String newStatus, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "UPDATE tasks SET status = ? WHERE user_id = ? AND name = ?";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setString(1, newStatus);
                    prepStatement.setInt(2, userId);
                    prepStatement.setString(3, inputName);
                    prepStatement.execute();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTaskCategory(String inputName, int newCategoryId, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "UPDATE tasks SET category_id = ? WHERE user_id = ? AND name = ?";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setInt(1, newCategoryId);
                    prepStatement.setInt(2, userId);
                    prepStatement.setString(3, inputName);
                    prepStatement.execute();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getTaskByName(String inputName, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT * FROM tasks WHERE name = ? AND user_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, inputName);
                    preparedStatement.setInt(2, userId);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        System.out.print(resultSet.getString(3) + " - ");
                        System.out.print(resultSet.getString(4) + " (");
                        String categoryName = returnNameCategory(resultSet.getInt(5));
                        System.out.println(categoryName + ")");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAllTasks(int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT * FROM tasks WHERE user_id = " + userId + "";

                PreparedStatement prep = connection.prepareStatement(query);
                ResultSet resultSet = prep.executeQuery(query);
                int count = 1;
                while (resultSet.next()) {
                    System.out.print(count++ + ") ");
                    System.out.print(resultSet.getString(2) + ": ");
                    if (resultSet.getString(3) != null) {
                        System.out.print(resultSet.getString(3) + " - ");
                    }
                    System.out.print(resultSet.getString(4) + " (");
                    String categoryName = returnNameCategory(resultSet.getInt(5));
                    System.out.println(categoryName + ")");
                }
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeTaskByName(String inputName, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "DELETE FROM tasks WHERE name = '" + inputName + "' AND user_id = ?";

                try (PreparedStatement prep = connection.prepareStatement(query)) {
                    prep.setInt(1, userId);
                    prep.execute(query);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeAllTasks(int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "DELETE FROM tasks WHERE user_id = " + userId + "";

                try (PreparedStatement prep = connection.prepareStatement(query)) {
                    prep.execute(query);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static String returnNameCategory(int categoryId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT name FROM categories WHERE categories.id = " + categoryId + "";

                try (PreparedStatement prep = connection.prepareStatement(query)) {
                    ResultSet rs = prep.executeQuery(query);
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "не принадлежит категории";
    }
}

