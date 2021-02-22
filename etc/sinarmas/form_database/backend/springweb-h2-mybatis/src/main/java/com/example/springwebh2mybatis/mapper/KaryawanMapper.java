package com.example.springwebh2mybatis.mapper;

import com.example.springwebh2mybatis.model.GetKaryawanDto;
import com.example.springwebh2mybatis.model.PostKaryawanDto;
import com.example.springwebh2mybatis.model.PutKaryawanDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KaryawanMapper {

  @Select("SELECT " +
          "Id_Kary as id," +
          "Nama_Kary as nama, " +
          "Alamat_Kary as alamat," +
          "RT, " +
          "RW, " +
          "Kecamatan, " +
          "Kelurahan, " +
          "No_Telp as telepon, " +
          "Input_Date, " +
          "Input_By, " +
          "Approve_Date, " +
          "Approve_By " +
          "FROM Mst_Kary " +
          "WHERE Id_Kary=IFNULL(#{id},Id_Kary) " +
          "AND Nama_Kary LIKE IFNULL(#{nama}||'%','%') " +
          "AND Alamat_Kary LIKE IFNULL(#{alamat}||'%','%') " +
          "AND RT LIKE IFNULL(#{rt}||'%','%') " +
          "AND RW LIKE IFNULL(#{rw}||'%','%') " +
          "AND Kecamatan LIKE IFNULL(#{kecamatan}||'%','%') " +
          "AND Kelurahan LIKE IFNULL(#{kelurahan}||'%','%') " +
          "AND No_Telp LIKE IFNULL(#{telepon}||'%','%') " +
          "AND Input_Date=IFNULL(#{input_date},Input_Date) " +
          "AND Input_By LIKE IFNULL(#{input_by}||'%','%') " +
          "AND Approve_Date=IFNULL(#{approve_date},Approve_Date) " +
          "AND Approve_By LIKE IFNULL(#{approve_by}||'%','%') ")
  List<GetKaryawanDto> getKaryawan(GetKaryawanDto karyawan);

  @Insert("INSERT INTO Mst_Kary " +
          "VALUES (default, #{nama}, #{alamat}, #{rt}, #{rw}, #{kecamatan}, #{kelurahan}, #{telepon}, #{input_date}, #{input_by}, #{approve_date}, #{approve_by})")
  int postKaryawan(PostKaryawanDto karyawan);

  @Update("UPDATE Mst_Kary " +
          "SET Nama_Kary=IfNull(#{nama},Nama_Kary), " +
          "Alamat_Kary=IfNull(#{alamat},Alamat_Kary), " +
          "RT=IfNull(#{rt},RT), " +
          "RW=IfNull(#{rw},RW), " +
          "Kecamatan=IfNull(#{kecamatan},Kecamatan), " +
          "Kelurahan=IfNull(#{kelurahan},Kelurahan), " +
          "No_Telp=IfNull(#{telepon},No_Telp), " +
          "Input_Date=IfNull(#{input_date},Input_Date), " +
          "Input_By=IfNull(#{input_by},Input_By), " +
          "Approve_Date=IfNull(#{approve_date},Approve_Date), " +
          "Approve_By=IfNull(#{approve_by},Approve_By) " +
          "WHERE Id_Kary=#{id}")
  int putKaryawan(PutKaryawanDto karyawan);

  @Delete("DELETE FROM Mst_Kary WHERE Id_Kary=#{id}")
  int deleteKaryawan(String id);
}
