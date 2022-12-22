package com.cybersoft.crm.repository;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface JdbcFunctional <T> {
    T processJDBC(Connection connection) throws SQLException;
}
