package bean;

import java.io.Serializable;

/**
 * Created by qiaoz on 2017/7/5.
 */
public class OrderItem implements Serializable{
    private static final long serialVersionUID = -5505953303868632392L;
    private Integer id;
    private int count;
    private double amount;
    private String title;
    private String author;
    private double price;
    private String imgPath="static/img/default.jpg";
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(Integer id, int count, double amount, String title, String author, double price, String imgPath, String orderId) {
        this.id = id;
        this.count = count;
        this.amount = amount;
        this.title = title;
        this.author = author;
        this.price = price;
        this.imgPath = imgPath;
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", count=" + count +
                ", amount=" + amount +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", imgPath='" + imgPath + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
