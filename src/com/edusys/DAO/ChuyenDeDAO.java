/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.DAO;

import com.edusys.entity.ChuyenDe;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{
    String insert_sql = "insert into chuyende(MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa)values(?,?,?,?,?,?)";
    String update_sql = "update chuyende set TenCD=?,HocPhi=?,ThoiLuong=?,Hinh=?,MoTa=? where MaCD=?";
    String delete_sql = "delete from chuyende where MaCD=?";
    String select_all_sql = "select*from chuyende";
    String select_by_id_sql = "select*from chuyende where MaCD=?";

    @Override
    public void insert(ChuyenDe entity) {
        JdbcHelper.update(insert_sql, 
                entity.getMaCD(),
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        JdbcHelper.update(update_sql, 
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa(),
                entity.getMaCD());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(delete_sql, id);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe>list = this.selectBySql(select_by_id_sql, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        
        return this.selectBySql(select_all_sql);
    }

    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe>list = new ArrayList<ChuyenDe>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                ChuyenDe entity = new ChuyenDe();
                entity.setMaCD(rs.getString("MaCD"));
                entity.setTenCD(rs.getString("TenCD"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setHinh(rs.getString("Hinh"));
                entity.setMoTa(rs.getString("MoTa"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
