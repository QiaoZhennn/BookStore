package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by qiaoz on 2017/6/14.
 */
public class JDBCUtils {
    private static DataSource ds = new ComboPooledDataSource();
    //将connection与当前线程绑定在一起，从而保证同一个线程只能获取同一个链接。
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();
    public static Connection getConnection() {
        Connection conn=threadLocal.get();
        if(conn==null){
            try {
                conn=ds.getConnection();
                //将新获取的链接与当前线程绑定。
                threadLocal.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void free() {
        //从当前线程获取链接。
        Connection connection=threadLocal.get();
        if(connection!=null) {
            DbUtils.closeQuietly(connection);
            threadLocal.remove();
        }
    }
}
