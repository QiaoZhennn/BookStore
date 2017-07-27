package servlet;

import bean.User;
import dao.impl.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qiaoz on 2017/6/14.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Username: "+username+"\tPassword: "+password);
        UserDAOImpl userDAO=new UserDAOImpl();
        User user=userDAO.login(username,password);
        request.getSession().setAttribute("user",user);
        if(user!=null){
            System.out.println("Login Succeed!");
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }else {
            String msg="Illegal username or password";
            request.setAttribute("msg",msg);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath()+"/pages/user/login.jsp");
            requestDispatcher.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
