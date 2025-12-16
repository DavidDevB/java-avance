package shop;

public class Main {
    public static void main(String[] args) {
        try {
            TestJdbc.readAllArticles();

            Article newArticle = new Article(0, "New Article", "New Brand", 99.99);
            TestJdbc.createArticle(newArticle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
