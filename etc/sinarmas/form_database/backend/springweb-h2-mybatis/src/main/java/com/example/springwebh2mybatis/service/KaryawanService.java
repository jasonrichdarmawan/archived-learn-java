package com.example.springwebh2mybatis.service;

import com.example.springwebh2mybatis.mapper.KaryawanMapper;
import com.example.springwebh2mybatis.model.GetKaryawanDto;
import com.example.springwebh2mybatis.model.PostKaryawanDto;
import com.example.springwebh2mybatis.model.PutKaryawanDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaryawanService {
  private final KaryawanMapper karyawanMapper;

  public KaryawanService(KaryawanMapper karyawanMapper) {
    this.karyawanMapper = karyawanMapper;
  }

  public List<GetKaryawanDto> getKaryawan(GetKaryawanDto karyawan) {
    return karyawanMapper.getKaryawan(karyawan);
  }

  public int postKaryawan(PostKaryawanDto karyawan) {
    return karyawanMapper.postKaryawan(karyawan);
  }

  public int putKaryawan(PutKaryawanDto karyawan) {
    return karyawanMapper.putKaryawan(karyawan);
  }

  public int deleteKaryawan(String id) {
    return karyawanMapper.deleteKaryawan(id);
  }
}
