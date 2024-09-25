package com.dev.pos.dao;

import com.dev.pos.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {

    public static <T> T execute(String sql, Object... params) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject((i + 1), params[i]);
        }

        if(sql.startsWith("SELECT")){
            return (T) preparedStatement.executeQuery();
        }
        return (T) (Boolean) (preparedStatement.executeUpdate() >0);
    }

}
