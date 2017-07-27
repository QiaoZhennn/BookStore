package bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by qiaoz on 2017/7/4.
 */
public class CartItem implements Serializable{
    private static final long serialVersionUID=1L;
    private Book book;
    private int count;
    private double amount;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        //使用BigDecimal解决double的计算精度问题。
        BigDecimal bigCount=new BigDecimal(count+"");
        BigDecimal bigPrice=new BigDecimal(book.getPrice()+"");
        BigDecimal multiply=bigCount.multiply(bigPrice);
        return multiply.doubleValue();
    }

}
