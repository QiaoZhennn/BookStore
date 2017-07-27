package test.listenerLearning;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
 * Created by qiaoz on 2017/7/9.
 */
public class Teacher implements HttpSessionActivationListener,Serializable{
    private static final long serialVersionUID = 6391420766476164035L;

    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("Passivate");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("Did Activate");
    }
}
