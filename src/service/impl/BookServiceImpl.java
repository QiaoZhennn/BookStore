package service.impl;

import bean.Book;
import bean.Page;
import dao.BookDAO;
import dao.impl.BookDAOImpl;
import service.BookService;

import java.util.List;

/**
 * Created by qiaoz on 2017/6/19.
 */
public class BookServiceImpl implements BookService {
    BookDAO bookDAO=new BookDAOImpl();
    @Override
    public List<Book> getBooks() {
        return bookDAO.getBookList();
    }

    @Override
    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    @Override
    public void deleteBook(String bookId) {
        bookDAO.deleteBook(bookId);
    }

    @Override
    public Book getBook(String bookId) {
        return bookDAO.getBookById(bookId);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public Page<Book> getPageBooks(String pageNo) {
        Page<Book> page=new Page<>();
        int defaultPageNo=1;
        try{
            defaultPageNo=Integer.parseInt(pageNo);
        }catch (Exception e){}
        page.setPageNo(defaultPageNo);
        return bookDAO.getPageBooks(page);
    }

    @Override
    public Page<Book> getPageBooksByPrice(String pageNo, String minPrice, String maxPrice) {
        Page<Book> page=new Page<>();
        int defaultPageNo=1;
        try{
            defaultPageNo=Integer.parseInt(pageNo);
        }catch (Exception e){}
        //设置一个默认的价格范围
        double defaultMinPrice=0;
        try{
            //以此来防止前端非法输入。
            defaultMinPrice=Double.parseDouble(minPrice);
        }catch (Exception e){}

        double defaultMaxPrice=Double.MAX_VALUE;
        try{
            defaultMaxPrice=Double.parseDouble(maxPrice);
        }catch (Exception e){}

        page.setPageNo(defaultPageNo);
        return bookDAO.getPageBooksByPrice(page,defaultMinPrice,defaultMaxPrice);
    }
}
