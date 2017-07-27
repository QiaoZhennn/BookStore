import Bean.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaoz on 2017/6/17.
 */
@WebServlet(name = "StudentServlet",urlPatterns = "/demo")
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentBean> list=new ArrayList<>();
        list.add(new StudentBean(1,"David","Male",25,"America"));
        list.add(new StudentBean(2,"Kim","Male",29,"Korea"));
        list.add(new StudentBean(3,"Osaka","Female",25,"Japan"));
        list.add(new StudentBean(4,"Baby","Female",30,"China"));
        request.setAttribute("students",list);
        request.getRequestDispatcher("/scope2.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
