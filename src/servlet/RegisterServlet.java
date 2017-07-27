package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import bean.User;
import dao.impl.UserDAOImpl;

/**
 * Created by qiaoz on 2017/6/14.
 */
@WebServlet(name = "RegisterServlet",urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        System.out.println("Username: "+username+"\tPassword: "+password+"\tEmail: "+email);
        UserDAOImpl userDAO=new UserDAOImpl();
        User selectedUser=userDAO.getUser(username);
        if(selectedUser==null){
            userDAO.register(new User(username,password,email));
            response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
        }else{
            String msg="用户名已存在";
            request.setAttribute("msg",msg);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath()+"/pages/user/regist.jsp");
            requestDispatcher.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
