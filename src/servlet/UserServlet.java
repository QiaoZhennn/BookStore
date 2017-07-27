package servlet;

import bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by qiaoz on 2017/6/17.
 */
@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAOImpl userDAO = new UserDAOImpl();
        User loginUser=userDAO.login(username, password);
        if (loginUser != null) {
            HttpSession session=request.getSession();
            session.setAttribute("user",loginUser);
            System.out.println("Login Succeed!");
            response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
        } else {
            System.out.println("Login fail");
            String msg = "Illegal username or password";
            request.setAttribute("msg", msg);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath() + "/pages/user/login.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String reqCode=request.getParameter("inputCode");
        HttpSession session=request.getSession();
        String sessionCode= (String) session.getAttribute("code");
        if(reqCode.equals(sessionCode)) {
            UserDAOImpl userDAO = new UserDAOImpl();
            User selectedUser = userDAO.getUser(username);
            if (selectedUser == null) {
                User user=new User(username, password, email);
                session.setAttribute("user",user);
                userDAO.register(user);
                response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
            } else {
                String msg = "Username already exists";
                request.setAttribute("msg", msg);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath() + "/pages/user/regist.jsp");
                requestDispatcher.forward(request, response);
            }
            session.removeAttribute("code");
        }else {
            String msg = "Wrong Validation code";
            request.setAttribute("msg", msg);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath() + "/pages/user/regist.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username=request.getParameter("username");
        UserDAOImpl userDAO = new UserDAOImpl();
        User selectedUser = userDAO.getUser(username);
        ObjectMapper objectMapper=new ObjectMapper();
        String msg;
        if (selectedUser == null) {
            msg=objectMapper.writeValueAsString("<font style='color:green'>用户名可用</font>");
        } else {
            msg=objectMapper.writeValueAsString("用户名已存在");
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(msg);
    }
}
