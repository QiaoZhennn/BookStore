package test.listenerLearning;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Created by qiaoz on 2017/7/9.
 */
public class Student implements HttpSessionBindingListener{
    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("Studnet has been added to sessionScope");

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("Studnet has been removed from sessionScope");

    }
}
