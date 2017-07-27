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
@WebServlet(name = "Login2Servlet", urlPatterns = "/demo5")
public class Login2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkMe = request.getParameter("checkMe");
        System.out.println("checkMe value: "+ checkMe);
        Cookie userNameCookie = new Cookie("username", username);
        Cookie passwordCookie = new Cookie("password", password);
        if ("checked".equals(checkMe)) {
            //将两个Cookie对象持久化。
            userNameCookie.setMaxAge(12000);
            passwordCookie.setMaxAge(12000);
        }else {
            userNameCookie.setMaxAge(-1);
            passwordCookie.setMaxAge(-1);
        }
        response.addCookie(userNameCookie);
        response.addCookie(passwordCookie);
        //假设用户名和密码均正确
            response.sendRedirect(request.getContextPath() + "/success.jsp");
    }
}
