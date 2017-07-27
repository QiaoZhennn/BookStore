package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by qiaoz on 2017/7/4.
 */
public class Cart implements Serializable{
    private static final long serialVersionUID=1L;

    private Map<String,CartItem> map=new LinkedHashMap<>();//保证添加到购物车中的顺序
    private int totalCount=0;
    private double totalAmount;
    public List<CartItem> getCartItems(){
        Collection<CartItem> values=map.values();
        return new ArrayList<CartItem>(values);
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    public int getTotalCount() {
        int totalCount=0;
        List<CartItem> cartItems=getCartItems();
        for(CartItem cartItem:cartItems){
            totalCount+=cartItem.getCount();
        }
        return totalCount;
    }

    public double getTotalAmount() {
        BigDecimal bigTotalAmount=new BigDecimal("0");
        BigDecimal bigAmount=null;
        List<CartItem> cartItems=getCartItems();
        for(CartItem cartItem:cartItems){
            bigAmount=new BigDecimal(cartItem.getAmount()+"");
            bigTotalAmount=bigTotalAmount.add(bigAmount);
        }
        return bigTotalAmount.doubleValue();
    }

    public void clearCart(){
        map.clear();
    }

    public void deleteCartItem(String bookId){
        map.remove(bookId);
    }

    public void updateCartItem(String bookId, String bookCount){
        //获取要更新的图书
        CartItem cartItem=map.get(bookId);
        try {
            int newCount = Integer.parseInt(bookCount);
            if(newCount>0) {
                cartItem.setCount(newCount);
            }
        }catch (NumberFormatException e){

        }
    }

    public void addBookToCart(Book book){
        //判断此时该项是否在购物车
        CartItem cartItem = map.get(book.getId() + "");
        if(cartItem==null){
            cartItem=new CartItem();
            cartItem.setBook(book);
            cartItem.setCount(1);
            map.put(book.getId()+"",cartItem);
        }else {
            cartItem.setCount(cartItem.getCount()+1);
        }
    }
}
