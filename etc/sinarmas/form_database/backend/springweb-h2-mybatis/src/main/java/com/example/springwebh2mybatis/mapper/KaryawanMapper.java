package com.example.springwebh2mybatis.mapper;

import com.example.springwebh2mybatis.model.PostKaryawanDto;
import com.example.springwebh2mybatis.model.PutKaryawanDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface KaryawanMapper {

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
}
