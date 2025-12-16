
package shop;

import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class TestJdbc {

    public static void readAllArticles() throws Exception {
        
        ArrayList<Article> articles = new ArrayList<Article>();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mariadb://localhost:3307/shop";
        String login = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url,login,password)) {
            String strSql = "SELECT * FROM t_articles";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(strSql)) {
                    while(resultSet.next()) {
                        int rsIdUser = resultSet.getInt(1);
                        String rsDescription = resultSet.getString(2);
                        String rsMarque = resultSet.getString(3);
                        double rsPrixUnitaire = resultSet.getDouble(4);
                        articles.add((new Article(rsIdUser, rsDescription, rsMarque, rsPrixUnitaire)));
                    }
                }
            }

            for (Article a : articles) {
                System.out.println(a.getIdArticle() + " | " + a.getDescription() + " | " + a.getBrand() + " | " + a.getUnitaryPrice());
            }
        }
    }

    public static void createArticle(Article article) throws Exception {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mariadb://localhost:3307/shop";
        String login = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url,login,password)) {
            String strSql = "INSERT INTO t_articles (Description, Brand, UnitaryPrice) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setString(1, article.getDescription());
                statement.setString(2, article.getBrand());
                statement.setDouble(3, article.getUnitaryPrice());
                if (statement.executeUpdate() == 1) {
                    System.out.println("Article created successfully.");
                } else {
                    System.out.println("Failed to create article.");
                }
            }

            System.out.println("Current Articles in Database:");
            readAllArticles();
            
        }
    }

    public static void updateArticle(int articleId, Article article) throws Exception {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mariadb://localhost:3307/shop";
        String login = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url,login,password)) {
            String strSql = "UPDATE t_articles SET Description = ?, Brand = ?, UnitaryPrice = ? WHERE IdArticle = ?";
            try (PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setString(1, article.getDescription());
                statement.setString(2, article.getBrand());
                statement.setDouble(3, article.getUnitaryPrice());
                statement.setInt(4, articleId);
                
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Article updated successfully.");
                } else {
                    System.out.println("Failed to update article or article not found.");
                }
            }

            System.out.println("Current Articles in Database:");
            readAllArticles();
        }
    }

    public static void deleteArticle(int articleId) throws Exception {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mariadb://localhost:3307/shop";
        String login = "root";
        String password = "weR(@!-Zo_ho(cJc";

        try (Connection connection = DriverManager.getConnection(url,login,password)) {
            String strSql = "DELETE FROM t_articles WHERE IdArticle = ?";
            try (PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setInt(1, articleId);
                
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Article deleted successfully.");
                } else {
                    System.out.println("Failed to delete article or article not found.");
                }
            }

            System.out.println("Current Articles in Database:");
            readAllArticles();
        }
    }
}