package bean;

import java.io.Serializable;

/**
 * Created by qiaoz on 2017/6/6.
 */
public class Book implements Serializable{
    private static final long serialVersionUID=1L;
    private int id;
    private String title;
    private String author;
    private double price;
    private int sales;
    private int stock;
    private String imgPath="static/img/default.jpg";

    public Book() {
    }

    public Book(String title, String author, double price, int sales, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
    }

    public Book(String title, String author, double price, int sales, int stock, String imgPath) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
    }

    public Book(int id, String title, String author, double price, int sales, int stock, String imgPath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
