package com.example.springwebh2mybatis.controller;

import com.example.springwebh2mybatis.model.GetKaryawanDto;
import com.example.springwebh2mybatis.model.PostKaryawanDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class KaryawanController {

  @GetMapping("/karyawan")
  public GetKaryawanDto getKaryawan(@RequestBody GetKaryawanDto karyawan) {
    return karyawan;
  }

  @PostMapping("/karyawan")
  public PostKaryawanDto postKaryawan(@Valid @RequestBody PostKaryawanDto karyawan) {
    return karyawan;
  }
}
