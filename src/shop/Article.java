package shop;

public class Article {

    private String description;
    private String brand;
    private double unitaryPrice;
    private int idArticle;

    public Article(int idArticle, String description, String brand, double unitaryPrice) {
        this.idArticle = idArticle;
        this.description = description;
        this.brand = brand;
        this.unitaryPrice = unitaryPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public double getUnitaryPrice() {
        return unitaryPrice;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setUnitaryPrice(double unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }
}
