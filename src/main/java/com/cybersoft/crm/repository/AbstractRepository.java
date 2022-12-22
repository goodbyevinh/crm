package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractRepository<T>{
    public T executeQuery(JdbcFunctional<T> processor) {
        T t = null;
        try{
            Connection connection = MysqlConnection.getConnection();
            t = processor.processJDBC(connection);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error executeQuery T: " + e.getMessage());
        }
        return t;
    }

    public List<T> executeListQuery(JdbcFunctional<List<T>> processor) {
        List<T> list = null;
        try{
            Connection connection = MysqlConnection.getConnection();
            list = processor.processJDBC(connection);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error executeListQuery T: " + e.getMessage());
        }
        return list;
    }

    public int executeUpdate(JdbcFunctional<Integer> processor) {
        int result = 0;
        try{
            Connection connection = MysqlConnection.getConnection();
            result = processor.processJDBC(connection);
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error executeUpdate T: " + e.getMessage());
        }
        return result;
    }
}
