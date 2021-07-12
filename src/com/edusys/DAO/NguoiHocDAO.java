/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.DAO;

import com.edusys.entity.NguoiHoc;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{
    String insert_sql = "insert into dbo.NguoiHoc(MaNH,HoTen,NgaySinh,GioiTinh,DienThoai,Email,GhiChu,MaNV,NgayDK)values(?,?,?,?,?,?,?,?,?)";
    String update_sql = "update dbo.NguoiHoc set HoTen=?,NgaySinh=?,GioiTinh=?,DienThoai=?,Email=?,GhiChu=?,MaNV=?,NgayDK=? where MaNH=?";
    String delete_sql = "delete from dbo.NguoiHoc where MaNH=?";
    String select_all_sql = "select*from dbo.NguoiHoc";
    String select_by_id_sql = "select*from dbo.NguoiHoc where MaNH = ?";
    String SELECT_NOT_IN_COURSE = "SELECT * FROM NGUOIHOC WHERE HoTen LIKE ? AND "
            + "MaNH NOT IN (SELECT MaNH FROM HOCVIEN WHERE MaKH = ?)";

    @Override
    public void insert(NguoiHoc entity) {
        JdbcHelper.update(insert_sql, 
                entity.getMaNH(),
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.getGioiTinh(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayDK());
    }

    @Override
    public void update(NguoiHoc entity) {
        JdbcHelper.update(update_sql, 
                entity.getHoTen(),
                entity.getNgaySinh(),
                entity.getGioiTinh(),
                entity.getDienThoai(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayDK(),
                entity.getMaNH());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(delete_sql, id);
    }

    @Override
    public NguoiHoc selectById(String maNH) {
        List<NguoiHoc>list = this.selectBySql(select_by_id_sql,maNH);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySql(select_all_sql);
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc>list = new ArrayList<NguoiHoc>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNH(rs.getString("MaNH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayDK(rs.getDate("NgayDK"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<NguoiHoc> selectByKeyword(String keyword){
        String sql="SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
    public List<NguoiHoc> selectNotlnCourse(int makh, String keywork){
        return this.selectBySql(SELECT_NOT_IN_COURSE, "%"+keywork+"%",makh);
    }
}
