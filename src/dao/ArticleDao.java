package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import shop.Article;

public class ArticleDao implements Dao<Article>{


    

    @Override
    public void readAll() throws Exception {
        // Implementation for reading all articles from the database
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

            
        }
    }
}
