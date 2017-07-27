package dao.impl;

import bean.OrderItem;
import dao.OrderItemDAO;

import java.util.List;

/**
 * Created by qiaoz on 2017/7/5.
 */
public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql="insert into order_items(count,amount,title,author,price,img_path,order_id)" +
                " values(?,?,?,?,?,?,?)";
        update(sql,orderItem.getCount(),orderItem.getAmount(),orderItem.getTitle(),orderItem.getAmount()
        ,orderItem.getPrice(),orderItem.getImgPath(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        String sql="select id,count,amount,title,author,price,img_path imgPath,order_id orderId " +
                "from order_items where order_id=?";
        List<OrderItem> list = getBeans(sql, orderId);
        return list;
    }

    @Override
    public void batchInsertOrderItems(Object[][] params) {
        String sql="insert into order_items(count,amount,title,author,price,img_path,order_id)" +
                " values(?,?,?,?,?,?,?)";
        batchUpdate(sql,params);
    }
}
