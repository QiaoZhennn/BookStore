package servlet.test;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by qiaoz on 2017/6/21.
 */
@WebServlet(name = "PersistServlet",urlPatterns = "/demo3")
public class PersistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("user3", "persistCookie");
        //持久化Cookie对象
        //当age>0时，Cookie对象在age秒之后失效
        //age=0时，立即失效
        //age<0时，默认为session生命周期。
        cookie.setMaxAge(60);
        response.addCookie(cookie);
    }

    @Test
    public void test(){
        int[] nums={2,4,1,7,3};
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){

            System.out.println(nums[i]);
        }
    }
}
