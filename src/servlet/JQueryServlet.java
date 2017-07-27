package servlet;

import bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qiaoz on 2017/7/11.
 */
@WebServlet(name = "JQueryServlet",urlPatterns = "/JQueryServlet")
public class JQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User("Trump","ieok23","trump@gmail.com");
        ObjectMapper objectMapper=new ObjectMapper();
        String string = objectMapper.writeValueAsString(user);
        response.getWriter().write(string);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("username");
        System.out.println(userName);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("响应成功");
    }
}
