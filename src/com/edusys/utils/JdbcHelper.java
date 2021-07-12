/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class JdbcHelper {
    static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl="jdbc:sqlserver://localhost:1433;databaseName=EduSys";
    static String username="sa";
    static String password="123";
    static{
        try{
            Class.forName(driver);
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    
    public static PreparedStatement getStmt(String sql, Object...args)throws SQLException{
        Connection conn = DriverManager.getConnection(dburl, username, password);
//        System.out.println("Kết nối dữ liệu thành công!!");
        PreparedStatement stmt = null;
        if(sql.trim().startsWith("{")){
            stmt = conn.prepareCall(sql);
        }else{
            stmt = conn.prepareStatement(sql);
        }
        for(int i = 0; i< args.length; i++){
            stmt.setObject(i+1, args[i]);
        }
        return stmt;
    }
    
    public static ResultSet query(String sql, Object...args)throws SQLException{
        try {
            PreparedStatement stmt = JdbcHelper.getStmt(sql, args);
        return stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static Object value(String sql, Object...args){
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int update(String sql, Object...args){
        try {
            PreparedStatement stmt = JdbcHelper.getStmt(sql, args);
            try{
                return stmt.executeUpdate();
            }
            finally{
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
