package dao;

import bean.OrderItem;

import java.util.List;

/**
 * Created by qiaoz on 2017/7/5.
 */
public interface OrderItemDAO {
    void saveOrderItem(OrderItem orderItem);
    List<OrderItem> getOrderItemsByOrderId(String orderId);
    void batchInsertOrderItems(Object[][] params);
}
