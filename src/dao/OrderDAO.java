package dao;

import bean.Order;
import bean.OrderItem;

import java.util.List;

/**
 * Created by qiaoz on 2017/7/5.
 */
public interface OrderDAO {
    void saveOrder(Order order);
    List<Order> getOrders();
    public void updateOrderState(String orderId,int state);
    List<Order> getOrdersByUserId(String userId);
}
