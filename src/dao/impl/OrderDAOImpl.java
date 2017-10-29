package dao.impl;

import bean.Order;
import dao.OrderDAO;

import java.util.List;

/**
 * Created by qiaoz on 2017/7/5.
 */
public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {
    @Override
    public void saveOrder(Order order) {
        //人为加入一个异常，演示事务的处理
        String sql="insert into orders(id,order_time,total_count,total_amount,state,user_id)" +
                " values(?,?,?,?,?,?)";
        update(sql,order.getId(),order.getOrderTime(),order.getTotalCount(),
                order.getTotalAmount(),order.getState(),order.getUserId());

    }

    @Override
    public List<Order> getOrders() {
        String sql="select id,order_time orderTime,total_count totalCount,total_amount totalAmount," +
                "state,user_id userId from orders";
        List<Order> list = getBeans(sql);
        return list;
    }

    @Override
    public void updateOrderState(String orderId, int state) {
        String sql="update orders set state=? where id=?";
        update(sql,state,orderId);
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        String sql="select id,order_time orderTime,total_count totalCount,total_amount totalAmount," +
                "state,user_id userId from orders where user_id=?";
        return getBeans(sql,userId);
    }
}
