package test;

import bean.Book;
import bean.Page;
import dao.impl.BookDAOImpl;

import java.util.List;

/**
 * Created by qiaoz on 2017/6/19.
 */
public class BookTest {
    public static void main(String[] args) {
        BookDAOImpl bookDAO=new BookDAOImpl();
        Page<Book> page=new Page<>();
        page.setPageNo(10);
        Page<Book> pageBooks=bookDAO.getPageBooks(page);
        System.out.println("Total Record+\t"+pageBooks.getTotalRecord());
        System.out.println("Total pageNo+\t"+pageBooks.getTotalPageNo());
        List list=pageBooks.getList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
