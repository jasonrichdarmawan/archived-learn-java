package com.example.springwebh2mybatis.service;

import com.example.springwebh2mybatis.mapper.KaryawanMapper;
import com.example.springwebh2mybatis.model.PostKaryawanDto;
import org.springframework.stereotype.Service;

@Service
public class KaryawanService {
  private final KaryawanMapper karyawanMapper;

  public KaryawanService(KaryawanMapper karyawanMapper) {
    this.karyawanMapper = karyawanMapper;
  }

  public int postKaryawan(PostKaryawanDto karyawan) {
    return karyawanMapper.postKaryawan(karyawan);
  }
}
