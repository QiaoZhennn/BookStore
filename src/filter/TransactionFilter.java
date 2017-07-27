package filter;

import utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by qiaoz on 2017/7/10.
 */
@WebFilter(filterName = "TransactionFilter",urlPatterns = "*")
public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            //如果没有异常，放行请求
            chain.doFilter(req, resp);
            //提交事务
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
                HttpServletResponse response=(HttpServletResponse)resp;
                HttpServletRequest request=(HttpServletRequest)req;
                response.sendRedirect(request.getContextPath()+"/pages/error/error.jsp");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            JDBCUtils.free();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
