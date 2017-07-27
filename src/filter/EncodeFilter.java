package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by qiaoz on 2017/7/5.
 */
@WebFilter(filterName = "EncodeFilter",urlPatterns = "*")
public class EncodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //在web.xml中设置字符集为utf-8,<context-param><param-name><param-value></></></>
        //获取当前应用的web初始化参数
        ServletContext servletContext = req.getServletContext();
        String encoding=servletContext.getInitParameter("encoding");
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
