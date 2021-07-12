/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.DAO;

import com.edusys.entity.HocVien;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer>{
    String insert_sql = "insert into HocVien(MaKH,MaNH,Diem)values(?,?,?)";
    String update_sql = "update HocVien set MaKH=?,MaNH=?,Diem=? where MaHV=?";
    String delete_sql = "delete from HocVien where MaHV=?";
    String select_all_sql = "select*from HocVien";
    String select_by_id_sql = "select*from HocVien where MaHV=?";
    

    @Override
    public void insert(HocVien entity) {
        JdbcHelper.update(insert_sql, 
//                entity.getMaHV(),
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JdbcHelper.update(update_sql, 
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem(),
                entity.getMaHV());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(delete_sql, id);
    }

    @Override
    public HocVien selectById(Integer id) {
        List<HocVien>list = this.selectBySql(select_by_id_sql, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySql(select_all_sql);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien>list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                HocVien entity = new HocVien();
                entity.setMaHV(rs.getInt("MaHV"));
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaNH(rs.getString("MaNH"));
                entity.setDiem(rs.getDouble("Diem"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql="SELECT * FROM HocVien WHERE MaKH=?";
        return this.selectBySql(sql, maKH);
    }
}
