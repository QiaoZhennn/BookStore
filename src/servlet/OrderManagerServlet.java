package servlet;

import bean.Order;
import bean.OrderItem;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by qiaoz on 2017/7/6.
 */
@WebServlet(name = "OrderManagerServlet",urlPatterns = "/OrderManagerServlet")
public class OrderManagerServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    protected void getOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.getOrders();
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
    }

    protected void getOrderItemsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId=request.getParameter("orderId");
        List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);
        request.setAttribute("orderItems",orderItems);
        request.getRequestDispatcher("/pages/manager/order_info.jsp").forward(request,response);
    }

    protected void getOrdersByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId=request.getParameter("userId");
        List<Order> orders = orderService.getOrdersByUserId(userId);
        request.setAttribute("ordersByUser",orders);
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);
    }

    protected void getOrdersById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId=request.getParameter("orderId");
        List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);
        request.setAttribute("orderItems",orderItems);
        request.getRequestDispatcher("/pages/manager/order_info.jsp").forward(request,response);
    }

    protected void updateOrderState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId=request.getParameter("orderId");
        int state= Integer.parseInt(request.getParameter("state"));
        orderService.updateOrderState(orderId,state);
        response.sendRedirect(request.getContextPath()+request.getHeader("Referer"));
    }
}
