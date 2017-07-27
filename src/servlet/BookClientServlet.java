package servlet;

import bean.Book;
import bean.Page;
import service.BookService;
import service.impl.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qiaoz on 2017/6/20.
 * 管理图书前台
 */
@WebServlet(name = "BookClientServlet", urlPatterns = "/BookClientServlet")
public class BookClientServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void getPageBooksByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        String minPrice = request.getParameter("min");
        String maxPrice = request.getParameter("max");
        Page<Book> pageBooksByPrice = bookService.getPageBooksByPrice(pageNo, minPrice, maxPrice);
        request.setAttribute("page", pageBooksByPrice);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath() + "/pages/manager/book.jsp");
        requestDispatcher.forward(request, response);
    }

//    protected void getPageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String pageNo = request.getParameter("pageNo");
//        Page<Book> page = bookService.getPageBooks(pageNo);
//        request.setAttribute("page", page);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath() + "/pages/manager/book.jsp");
//        requestDispatcher.forward(request, response);
//    }
}
