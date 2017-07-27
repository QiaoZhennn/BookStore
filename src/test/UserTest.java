package test;

import bean.User;
import dao.impl.UserDAOImpl;

/**
 * Created by qiaoz on 2017/6/14.
 */
public class UserTest {
    public static void main(String[] args) {
        UserDAOImpl userDAO = new UserDAOImpl();
        String username = "joey";
        String password = "123456";
        String email = "joey@gmail.com";
        User selectedUser = userDAO.getUser(username);
        if (selectedUser == null) {
            userDAO.register(new User(username, password, email));
            System.out.println("Register Succeed");
        } else {
            System.out.println("Username has already existed");
        }
        String username2 = "chailinyan";
        String password2 = "123456";
        if (userDAO.login(username2, password2)!=null) {
            System.out.println(userDAO.login(username2, password2));
        } else {
            System.out.println("Login failure");
        }
    }

}
