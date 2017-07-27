package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by qiaoz on 2017/7/5.
 */
//运用过滤器检查结账时，用户是否登陆。原理：拦截结账的Servlet，获取放在Session域中的User对象。
@WebFilter(filterName = "LoginFilter",urlPatterns = "/OrderClientServlet")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user!=null) {
            chain.doFilter(req, resp);
        }else {
            //还没有登陆，设置一个错误消息，放到request域中。
            request.setAttribute("msg","Need to login first");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/user/login.jsp");
            requestDispatcher.forward(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
