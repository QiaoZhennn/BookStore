package dao;

import bean.Book;
import bean.Page;

import java.util.List;

/**
 * Created by qiaoz on 2017/6/19.
 */
public interface BookDAO {
    void saveBook(Book book);
    void deleteBook(String bookId);
    void updateBook(Book book);
    Book getBookById(String bookId);
    List<Book> getBookList();

    /**
     *获取分页的Page对象。
     * @param page 传入时，只有pageNo一个属性
     * @return page 包含Page的全部属性
     */
    Page<Book> getPageBooks(Page<Book> page);
    Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice, double maxPrice);
    void batchUpdateSalesAndStock(Object[][] params);
}
