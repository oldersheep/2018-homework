package com.xxx.template;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws Exception {
        return this.dataSource.getConnection();
    }

    private PreparedStatement prepareStatement(Connection connection, String sql) throws Exception {
        return connection.prepareStatement(sql);
    }

    private ResultSet executeQuery(PreparedStatement statement, Object[] values) throws Exception {
        for (int i = 0; i<values.length; i++) {
            statement.setObject(i, values);
        }
        return statement.executeQuery();
    }

    private void closeResultSet(ResultSet rs) throws Exception {
        rs.close();
    }

    private void closePreparedStatement(PreparedStatement statement) throws Exception {
        statement.close();
    }

    // 通常是放回连接池
    private void closeConnection(Connection connection) throws Exception {
        connection.close();
    }

    private List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNmu = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNmu++));
        }
        return result;
    }

    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values){
        try{
            // 获取连接
            Connection connection = this.getConnection();
            // 创建语句集
            PreparedStatement statement = this.prepareStatement(connection, sql);
            // 执行语句集，获得数据集
            ResultSet rs = this.executeQuery(statement, values);

            // 解析语句集
            List<?> result = this.parseResultSet(rs, rowMapper);

            // 关闭
            this.closeResultSet(rs);
            this.closePreparedStatement(statement);
            this.closeConnection(connection);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // public abstract Object processResult(ResultSet rs) throws Exception;
}
