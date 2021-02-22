package com.example.springwebh2mybatis.controller;

import com.example.springwebh2mybatis.model.GetKaryawanDto;
import com.example.springwebh2mybatis.model.PostKaryawanDto;
import com.example.springwebh2mybatis.model.PutKaryawanDto;
import com.example.springwebh2mybatis.service.KaryawanService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/v1")
@Validated
public class KaryawanController {

  private final KaryawanService karyawanService;

  public KaryawanController(KaryawanService karyawanService) {
    this.karyawanService = karyawanService;
  }

  @GetMapping("/karyawan")
  public GetKaryawanDto getKaryawan(@RequestBody GetKaryawanDto karyawan) {
    return karyawan;
  }

  @PostMapping("/karyawan")
  public PostKaryawanDto postKaryawan(@Valid @RequestBody PostKaryawanDto karyawan) {
    return karyawanService.postKaryawan(karyawan) == 1 ? karyawan : null;
  }

  @PutMapping("/karyawan")
  public PutKaryawanDto putKaryawan(@Valid @RequestBody PutKaryawanDto karyawan) {
    return karyawan;
  }

  @DeleteMapping("/karyawan/{id}")
  public String deleteKaryawan(@PathVariable("id") @NotEmpty String id) {
    return id;
  }
}
