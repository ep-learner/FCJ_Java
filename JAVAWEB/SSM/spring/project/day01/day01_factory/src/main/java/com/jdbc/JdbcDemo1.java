package com.jdbc;

import java.sql.*;

public class JdbcDemo1 {
    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java", "root", "admin");
//        PreparedStatement ps_insert = c.prepareStatement("insert into table1(name) values( 'first_name')");
//        ps_insert.execute();
//        PreparedStatement ps_update = c.prepareStatement("update table1 set name = 'changed' where id = 1 ");
//        ps_update.execute();
//        PreparedStatement ps_select = c.prepareStatement("select id , name from  table1 where id = 1");
//
//        PreparedStatement ps_delete = c.prepareStatement("delete from  table1 where name = 'first_name'");
//        ps_delete.execute();
        PreparedStatement ps = c.prepareStatement("select id , name from  table1 where id = 1");
        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            System.out.println(rs.getInt(0));
//        }
        rs.close();
        ps.close();
        c.close();
    }

}
