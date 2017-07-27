package dao.impl;

import bean.Book;
import bean.Page;
import dao.BookDAO;

import java.util.List;

/**
 * Created by qiaoz on 2017/6/19.
 */
public class BookDAOImpl extends BasicDAO<Book> implements BookDAO {
    @Override
    public void saveBook(Book book) {
        String sql = "insert into books(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public void deleteBook(String bookId) {
        String sql="delete from books where id=?";
        update(sql,bookId);
    }

    @Override
    public void updateBook(Book book) {
        String sql="update books set title=?,author=?,price=?,sales=?,stock=? where id=?";
        update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getId());
    }

    @Override
    public Book getBookById(String bookId) {
        String sql="select id,title,author,price,sales,stock,img_path as imgPath from books where id=?";
        Book book=getBean(sql,bookId);
        return book;
    }

    @Override
    public List<Book> getBookList() {
        String sql = "select id,title,author,price,sales,stock,img_path as imgPath from books";
        List<Book> list = getBeans(sql);
        return list;
    }

    @Override
    public Page<Book> getPageBooks(Page<Book> page) {
        String sql="select count(*) from books";
        long count = (long) getValue(sql);
        page.setTotalRecord((int) count);
        String sql2="select id,title,author,price,sales,stock,img_path as imgPath from books limit ?,?";
        List<Book> list = getBeans(sql2, (page.getPageNo() - 1) * page.PAGE_SIZE, page.getPageSize());
        page.setList(list);
        return page;
    }

    @Override
    public Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice, double maxPrice) {
        String sql="select count(*) from books where price between ? and ?";
//        getValue()的返回值是一个Object类型，直接转int可能会出错，需要先转long，然后转int
        long count = (long) getValue(sql,minPrice,maxPrice);
        page.setTotalRecord((int) count);
        String sql2="select id,title,author,price,sales,stock,img_path as imgPath from books where price between ? and ? limit ?,?";
        List<Book> list = getBeans(sql2, minPrice,maxPrice,(page.getPageNo() - 1) * page.PAGE_SIZE, page.getPageSize());
        page.setList(list);
        return page;
    }

    @Override
    public void batchUpdateSalesAndStock(Object[][] params) {
        String sql="update books set sales=?,stock=? where id=?";
        batchUpdate(sql,params);
    }
}
