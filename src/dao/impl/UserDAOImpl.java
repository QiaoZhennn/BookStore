package dao.impl;

import bean.User;
import dao.UserDAO;

/**
 * Created by qiaoz on 2017/6/14.
 */
public class UserDAOImpl extends BasicDAO<User> implements UserDAO {
    @Override
    public void register(User user) {
        String sql="insert into users(username,password,email) value(?,?,?)";
        update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User getUser(String userName) {
        String sql="select id,username,password,email from users where username=?";
        User user = getBean(sql, userName);
        return user;
    }

    @Override
    public User login(String userName, String password) {
        User user=getUser(userName);
        if(user!=null){
        String selectedPassword=(getUser(userName).getPassword());
        if(password.equals(selectedPassword)){
            return user;
        }}
        return null;
    }
}
