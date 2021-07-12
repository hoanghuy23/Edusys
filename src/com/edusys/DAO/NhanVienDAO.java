/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.DAO;

import com.edusys.entity.NhanVien;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String>{
    
    String insert_sql = "INSERT INTO NHANVIEN(MaNV,HoTen,MatKhau,VaiTro) VALUES(?,?,?,?)";
    String update_sql = "UPDATE NHANVIEN set MatKhau=?,HoTen=?,VaiTro=? WHERE MaNV=?";
    String delete_sql = "DELETE FROM NHANVIEN WHERE MaNV=?";
    String select_all_sql = "SELECT * FROM NHANVIEN";
    String select_by_id_sql = "SELECT * FROM NHANVIEN WHERE MaNV=?";
    
  

    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(insert_sql, 
                entity.getMaNV(),
                entity.getHoTen(),
                entity.getMatKhau(),
                entity.getVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
       JdbcHelper.update(update_sql, 
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.getVaiTro(),
                entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(delete_sql, id);
    }

    @Override
    public NhanVien selectById(String id) {
//        String sql = "select * from NhanVien where MaNV = ? ,MatKhau = ?";
        List<NhanVien> list = this.selectBySql(select_by_id_sql, id);
        return list.size()>0 ?list.get(0) : null;
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(select_all_sql);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while(rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
            } finally{
                rs.getStatement().getConnection().close();
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    
    
}
