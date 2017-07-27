package servlet.test;

import bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiaoz on 2017/7/12.
 */
@WebServlet(name = "JsonServlet",urlPatterns = "/jsonServlet")
public class JsonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper objectMapper=new ObjectMapper();
        User user=new User("Chill","123456","chill@gmail.com");
        String JSONString=objectMapper.writeValueAsString(user);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(JSONString);
        System.out.println(JSONString);
        User user1 = objectMapper.readValue(JSONString, User.class);
        System.out.println("User1: "+user1);
    }

    @Test
    public void test2(){
        Map<String,User> map=new HashMap<>();
        map.put("user1",new User("Chill","123456","chill@gmail.com"));
        map.put("user2",new User("Roserve","1wwe456","reserve@gmail.com"));
        ObjectMapper objectMapper=new ObjectMapper();
        String string="";
        try {
            string = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(string);
    }
}
