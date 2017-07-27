package servlet;

import bean.Cart;
import bean.User;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by qiaoz on 2017/7/5.
 */
@WebServlet(name = "OrderClientServlet",urlPatterns = "/OrderClientServlet")
public class OrderClientServlet extends BaseServlet {

    private OrderService orderService=new OrderServiceImpl();

    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        Cart cart=(Cart)session.getAttribute("cart");
        String orderId = orderService.createOrder(user,cart);
        session.setAttribute("orderId",orderId);
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
