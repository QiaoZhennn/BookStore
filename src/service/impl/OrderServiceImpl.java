package service.impl;

import bean.*;
import dao.BookDAO;
import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.impl.BookDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderItemDAOImpl;
import service.OrderService;

import java.util.Date;
import java.util.List;

/**
 * Created by qiaoz on 2017/7/5.
 */
public class OrderServiceImpl implements OrderService {
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    BookDAO bookDAO = new BookDAOImpl();

    @Override
    public String createOrder(User user, Cart cart) {
        String orderId = System.currentTimeMillis() + user.getId() + "";
        Order order = new Order(orderId, new Date(), cart.getTotalCount(), cart.getTotalAmount(), 0, user.getId());
        //将订单保存到数据库中
        orderDAO.saveOrder(order);
        //获取购物车中所有的购物项
        List<CartItem> cartItems = cart.getCartItems();
        Object[][] itemParams = new Object[cartItems.size()][];
        Object[][] bookParams = new Object[cartItems.size()][];
        //遍历得到每一个购物项
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            int count = cartItem.getCount();
            double amount = cartItem.getAmount();
            Book book = cartItem.getBook();
            String title = book.getTitle();
            String author = book.getAuthor();
            double price = book.getPrice();
            String imgPath = book.getImgPath();
            itemParams[i]=new Object[]{count,amount,title,author,price,imgPath,orderId};
//            OrderItem orderItem = new OrderItem(null, count, amount, title, author, price, imgPath, orderId);
//            orderItemDAO.saveOrderItem(orderItem);

            //获取图书的库存和销量
            Integer sales = book.getSales();
            Integer stock = book.getStock();
            book.setSales(book.getSales()+count);
            book.setStock(book.getStock()-count);
            bookParams[i]=new Object[]{sales+count,stock-count,book.getId()};
//            bookDAO.updateBook(book);
        }
        orderItemDAO.batchInsertOrderItems(itemParams);
        bookDAO.batchUpdateSalesAndStock(bookParams);
        cart.clearCart();
        return orderId;
    }

    @Override
    public List<Order> getOrders() {
        return orderDAO.getOrders();
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        return orderItemDAO.getOrderItemsByOrderId(orderId);
    }

    @Override
    public void updateOrderState(String orderId, int state) {
        orderDAO.updateOrderState(orderId,state);
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderDAO.getOrdersByUserId(userId);
    }
}
