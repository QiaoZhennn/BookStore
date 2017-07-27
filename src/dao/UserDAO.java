package dao;

import bean.User;

/**
 * Created by qiaoz on 2017/6/14.
 */
public interface UserDAO {
    void register(User user);
    User getUser(String userName);
    User login(String userName, String password);
}
