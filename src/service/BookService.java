package service;

import bean.Book;
import bean.Page;

import java.util.List;

/**
 * Created by qiaoz on 2017/6/19.
 */
public interface BookService {
    List<Book> getBooks();
    void saveBook(Book book);
    void deleteBook(String bookId);
    Book getBook(String bookId);
    void updateBook(Book book);
    /**
     *获取分页的Page对象。
     * @param pageNo 传入时，只有pageNo一个属性
     * @return page 包含Page的全部属性
     */
    Page<Book> getPageBooks(String pageNo);

    /**
     * 根据价格获取分页的图书信息
     * @param pageNo
     * @param minPrice
     * @param maxPrice
     * @return
     */
    Page<Book> getPageBooksByPrice(String pageNo,String minPrice, String maxPrice);
}
