package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by qiaoz on 2017/6/17.
 */
@WebServlet(name = "BaseServlet")
/**
 * 专门用来被继承的Servlet
 */
public class BaseServlet extends HttpServlet implements Serializable{
    private static final long serialVersionUID = 1054715032181617288L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8"); //从浏览器请求到servlet的数据会出现乱码，所以，在获取请求前，必须设置字符集。
        String method = request.getParameter("method");
        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this,request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
