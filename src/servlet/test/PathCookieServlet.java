package servlet.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qiaoz on 2017/6/21.
 */
@WebServlet(name = "PathCookieServlet",urlPatterns = "/demo4")
public class PathCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie=new Cookie("user4","pathCookie");
        //setPath()方法的路径由浏览器解析。所以需要加getContextPath()
        cookie.setPath(request.getContextPath()+"/hello");
        response.addCookie(cookie);
    }
}
