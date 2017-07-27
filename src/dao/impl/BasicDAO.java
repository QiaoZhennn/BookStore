package dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import javax.sql.DataSource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by qiaoz on 2017/6/14.
 */
public abstract class BasicDAO<T> {
    private  QueryRunner qr=new QueryRunner();
    private DataSource ds;
    private Class<T> type;

    public BasicDAO() {
        Class clazz=this.getClass();
        ParameterizedType parameterizedType= (ParameterizedType) clazz.getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        this.type= (Class<T>) actualTypeArguments[0];
    }

    //通用的更新数据库的方法
    public int update(String sql,Object...params){
        int len=0;
        Connection conn=JDBCUtils.getConnection();
        try {
            len=qr.update(conn,sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(conn!=null) {
                //增加了事务，所以将关闭链接的方法放在事务处，通过过滤器添加事务。
                //JDBCUtils.free(conn);
            }
        }
        return len;
    }

    //通用的查询单个对象的方法
    public T getBean(String sql,Object...params){
        T bean=null;
        Connection conn=JDBCUtils.getConnection();
        try {
            bean = qr.query(conn, sql, new BeanHandler<T>(type), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(conn!=null) {
                //JDBCUtils.free(conn);
            }
        }
        return bean;
    }

    //查询多个实体对象的方法
    public List<T> getBeans(String sql,Object...params){
        List<T> list=null;
        Connection conn=JDBCUtils.getConnection();
        try {
            list=qr.query(conn,sql,new BeanListHandler<T>(type),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(conn!=null) {
                //JDBCUtils.free(conn);
            }
        }
        return list;
    }

    //查询单值的方法
    public Object getValue(String sql,Object...params){
        Object obj=null;
        Connection conn=JDBCUtils.getConnection();
        try {
            obj=qr.query(conn,sql,new ScalarHandler<T>(),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(conn!=null) {
                //JDBCUtils.free(conn);
            }
        }
        return obj;
    }

    //批量更新的方法
    public int[] batchUpdate(String sql,Object[][] params){
        int[] results=null;
        Connection conn=JDBCUtils.getConnection();
        try {
            results=qr.batch(conn,sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(conn!=null) {
                //JDBCUtils.free(conn);
            }
        }
        return results;
    }
}
