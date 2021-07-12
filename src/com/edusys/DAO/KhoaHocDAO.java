/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.DAO;

import com.edusys.entity.KhoaHoc;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>{
    String insert_sql = "insert into KhoaHoc(MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV,NgayTao)values(?,?,?,?,?,?,?)";
    String update_sql = "update KhoaHoc set MaCD=?,HocPhi=?,ThoiLuong=?,NgayKG=?,GhiChu=?,MaNV=?,NgayTao=? where MaKH=?";
    String delete_sql = "delete from KhoaHoc where MaKH=?";
    String select_all_sql = "select*from KhoaHoc";
    String select_by_id_sql = "select*from KhoaHoc where MaKH=?";
    String SELECT_BY_CD = "SELECT * FROM KHOAHOC WHERE MaCD = ?";

    @Override
    public void insert(KhoaHoc entity) {
        JdbcHelper.update(insert_sql,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc entity) {
        JdbcHelper.update(update_sql, 
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayTao(),
                entity.getMaKH());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(delete_sql, id);
    }

    @Override
    public KhoaHoc selectById(Integer id) {
        List<KhoaHoc>list = this.selectBySql(select_by_id_sql, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySql(select_all_sql);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc>list = new ArrayList<KhoaHoc>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setNgayKG(rs.getDate("NgayKG"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                entity.setMaCD(rs.getString("MaCD"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<KhoaHoc> selectByChuyenDe(String macd){ 
        return this.selectBySql(SELECT_BY_CD, macd);
    }
    public List<Integer> selectYears(){
        String sql = "SELECT DISTINCT year(NgayKG) Year FROM KHOAHOC ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
            
        }
    }
}
