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
@WebServlet(name = "CreateCookieServlet", urlPatterns = "/CreateCookieServlet")
public class CreateCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie1 = new Cookie("user", "admin");
        Cookie cookie2 = new Cookie("user", "superAdmin");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println("name: " + name + "\tvalue: " + value);
            }
        }
    }
}
