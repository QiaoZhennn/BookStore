package test;

import bean.Order;
import bean.OrderItem;
import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderItemDAOImpl;
import org.junit.Test;

import java.util.Date;

/**
 * Created by qiaoz on 2017/7/5.
 */
public class OrderTest {
    OrderDAO orderDAO=new OrderDAOImpl();
    OrderItemDAO orderItemDAO=new OrderItemDAOImpl();

    @Test
    public void test1(){
        Order order = new Order("17699900001", new Date(), 6, 600, 0, 2);
        orderDAO.saveOrder(order);

        OrderItem orderItem1 = new OrderItem(null, 2, 200, "title1", "author1", 100, null, "17699900001");
        OrderItem orderItem2 = new OrderItem(null, 4, 400, "title2", "author2", 100, null, "17699900001");
        orderItemDAO.saveOrderItem(orderItem1);
        orderItemDAO.saveOrderItem(orderItem2);
    }
}
