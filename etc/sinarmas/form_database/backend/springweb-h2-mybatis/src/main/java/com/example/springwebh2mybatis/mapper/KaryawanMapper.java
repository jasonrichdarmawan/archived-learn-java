package com.example.springwebh2mybatis.mapper;

import com.example.springwebh2mybatis.model.PostKaryawanDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KaryawanMapper {

  @Insert("INSERT INTO Mst_Kary VALUES (default, #{nama}, #{alamat}, #{rt}, #{rw}, #{kecamatan}, #{kelurahan}, #{telepon}, #{input_date}, #{input_by}, #{approve_date}, #{approve_by})")
  int postKaryawan(PostKaryawanDto karyawan);
}
