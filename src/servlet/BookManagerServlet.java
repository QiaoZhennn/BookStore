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
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by qiaoz on 2017/6/19.
 */
@WebServlet(name = "BookManagerServlet",urlPatterns = "/BookManagerServlet")
public class BookManagerServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

//    protected void getPageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Book> books = bookService.getPageBooks();
//        request.setAttribute("books",books);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath()+"/pages/manager/book_manager.jsp");
//        requestDispatcher.forward(request,response);
//    }

    protected void getPageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        Page<Book> page = bookService.getPageBooks(pageNo);
        request.setAttribute("page",page);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath()+"/pages/manager/book_manager.jsp");
        requestDispatcher.forward(request,response);
    }

    protected void deleteBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("bookId");
        bookService.deleteBook(id);
        getPageBooks(request,response);
    }

    protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("bookId");
        Book book = bookService.getBook(id);
        request.setAttribute("book",book);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/manager/book_edit.jsp");
        requestDispatcher.forward(request,response);
    }

    protected void updateBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("bookId");
//        Book book = bookService.getBook(id);
        String title=request.getParameter("book_name");
        String author=request.getParameter("book_author");
        Double price=Double.parseDouble(request.getParameter("book_price"));
        int sales= Integer.parseInt(request.getParameter("book_sales"));
        int stock= Integer.parseInt(request.getParameter("book_stock"));
        request.setAttribute("newBook",new Book(title,author,price,sales,stock));
        getPageBooks(request,response);
    }

    protected void updateOrSaveBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("bookId");
        String title=request.getParameter("book_name");
        String author=request.getParameter("book_author");
        Double price=Double.parseDouble(request.getParameter("book_price"));
        int sales= Integer.parseInt(request.getParameter("book_sales"));
        int stock= Integer.parseInt(request.getParameter("book_stock"));

        if("".equals(id)){
            Book book = new Book(title, author, price, sales, stock);
            bookService.saveBook(book);
        }else {
            Book book = new Book(Integer.parseInt(id),title, author, price, sales, stock,null);
            bookService.updateBook(book);
        }
        getPageBooks(request,response);
    }
}
