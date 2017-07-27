package service;

import bean.Cart;
import bean.Order;
import bean.OrderItem;
import bean.User;

import java.util.List;

/**
 * Created by qiaoz on 2017/7/5.
 */
public interface OrderService {
    String createOrder(User user, Cart cart);
    List<Order> getOrders();
    List<OrderItem> getOrderItemsByOrderId(String orderId);
    void updateOrderState(String orderId, int state);
    List<Order> getOrdersByUserId(String userId);

}
