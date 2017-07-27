package servlet;

import bean.Book;
import bean.Cart;
import bean.CartItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.BookService;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiaoz on 2017/7/4.
 */
@WebServlet(name = "CartServlet", urlPatterns = "/CartServlet")
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        Book bookById = bookService.getBook(bookId);
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        //将图书添加到购物车
        cart.addBookToCart(bookById);
        Map<String, CartItem> map = cart.getMap();
        CartItem cartItem = map.get(bookId);
        int count = cartItem.getCount();
        Integer stock = bookById.getStock();
        if (count > stock) {
            session.setAttribute("msg", "Max stock of this book is " + stock);
            cartItem.setCount(stock);
        } else {
            session.setAttribute("bookTitle", bookById.getTitle());
        }
        String header = request.getHeader("Referer");
        response.sendRedirect(header);
    }

    protected void emptyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.clearCart();
        }
        response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
    }

    protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.deleteCartItem(bookId);
        }
        String header = request.getHeader("Referer");
        response.sendRedirect(header);
    }

    protected void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String bookCount = request.getParameter("bookCount");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.updateCartItem(bookId, bookCount);
        }
        //获取购物车中图书的总数量，总金额，金额小计
        int totalCount=cart.getTotalCount();
        double totalAmount=cart.getTotalAmount();
        CartItem cartItem = cart.getMap().get(bookId);
        double amount = cartItem.getAmount();
        Map<String,Object> map=new HashMap<>();
        map.put("amount",amount);
        map.put("totalCount",totalCount);
        map.put("totalAmount",totalAmount);
        ObjectMapper objectMapper=new ObjectMapper();
        String string = objectMapper.writeValueAsString(map);
        response.getWriter().write(string);
//        String header = request.getHeader("Referer");
//        response.sendRedirect(header);
    }
}
