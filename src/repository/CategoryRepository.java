package repository;

import entity.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryRepository extends Configs {

    public static void addCategory(Category category, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String add = "INSERT INTO categories (name, user_id) values (?, ?)";

                try (PreparedStatement prepStatement = connection.prepareStatement(add)) {
                    prepStatement.setString(1, category.getName());
                    prepStatement.setInt(2, userId);
                    prepStatement.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCategory(String inputName, String newName, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "UPDATE categories SET name = ? WHERE name = ? AND user_id = ?";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setString(1, newName);
                    prepStatement.setString(2, inputName);
                    prepStatement.setInt(3, userId);
                    prepStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getCategoryByName(String inputName, int userId) {
        int categoryId = getIdCategory(inputName, userId);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT tasks.name FROM tasks WHERE category_id = " + categoryId + "";

                try (PreparedStatement prep = connection.prepareStatement(query)) {
                    ResultSet resultSet = prep.executeQuery(query);
                    System.out.println("=============================");
                    System.out.print(inputName + " - ");
                    if (!resultSet.next()) {
                            System.out.print("отсутствуют задачи");
                    } else {
                        resultSet.previous();
                        while (resultSet.next()) {
                            System.out.print(resultSet.getString(1) + ", ");
                        }
                    }
                    System.out.println("\n" + "=============================");
                    resultSet.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAllCategoriesWithTasks(int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT categories.user_id ,categories.name, tasks.name FROM categories LEFT JOIN tasks " +
                        "ON categories.id = tasks.category_id";

                try (Statement statement = connection.createStatement()) {
                    ResultSet resultSet = statement.executeQuery(query);
                    String temp = "";
                    int count = 1;
                    System.out.print("=============================");
                    while (resultSet.next()) {
                        if (userId == resultSet.getInt(1)) {
                            if (resultSet.getString(3) == null) {
                                System.out.print("\n" + count++ + ") " + resultSet.getString(2) + " - ");
                                System.out.print("отсутсвуют задачи");
                            } else {
                                if (!resultSet.getString(2).equals(temp)) {
                                    System.out.print("\n" + count++ + ") " + resultSet.getString(2) + " - ");
                                }
                                System.out.print(resultSet.getString(3) + ", ");
                            }
                            temp = resultSet.getString(2);
                        }
                    }
                    System.out.print("\n" + count + ") Задачи без категории: ");
                    getAllTasksWithOutCategory(userId);
                    System.out.println("\n" + "=============================");
                    resultSet.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeCategoryByName(String inputName, int userId) {
        removeIdFromTask(inputName,userId);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "DELETE FROM categories WHERE name = '" + inputName + "' AND user_id = " + userId + "";

                Statement statement = connection.createStatement();
                statement.execute(query);
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeAllCategories(int userId) {
        removeAllIdFromTask(userId);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "DELETE FROM categories WHERE user_id = " + userId + "";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.execute(query);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> getAllCategoriesAndNumberLines(int userId) {
        ArrayList<Integer> listOfNumbersLines = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT * FROM categories WHERE user_id = " + userId + "";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    ResultSet rs = prepStatement.executeQuery(query);
                    int count = 0;
                    while (rs.next()) {
                        System.out.println(++count + ") " + rs.getString(2));
                        listOfNumbersLines.add(rs.getInt(1));
                    }
                    return listOfNumbersLines;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfNumbersLines;
    }



    private static void getAllTasksWithOutCategory(int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT tasks.user_id, tasks.name, categories.name FROM tasks LEFT JOIN categories " +
                        "ON categories.id = tasks.category_id";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    ResultSet resultSet = prepStatement.executeQuery(query);
                    while (resultSet.next()) {
                        if (userId == resultSet.getInt(1)) {
                            if (resultSet.getString(3) == null) {
                                System.out.print(resultSet.getString(2) + ", ");
                            }
                        }
                    }
                    resultSet.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removeIdFromTask(String inputName, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "UPDATE tasks SET tasks.category_id = ? WHERE category_id = ? AND user_id = ?";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setInt(1, 0);
                    prepStatement.setInt(2, getIdCategory(inputName, userId));
                    prepStatement.setInt(3, userId);
                    prepStatement.execute();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removeAllIdFromTask(int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "UPDATE tasks SET tasks.category_id = ? WHERE user_id = ?";

                try (PreparedStatement prepStatement = connection.prepareStatement(query)) {
                    prepStatement.setInt(1, 0);
                    prepStatement.setInt(2, userId);
                    prepStatement.execute();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getIdCategory(String inputName, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String query = "SELECT * FROM categories WHERE categories.name = ? AND categories.user_id = ?";

                try (PreparedStatement prep = connection.prepareStatement(query)) {
                    prep.setString(1, inputName);
                    prep.setInt(2, userId);
                    ResultSet rs = prep.executeQuery();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
