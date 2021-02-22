package com.example.springwebh2mybatis.controller;

import com.example.springwebh2mybatis.model.GetKaryawanDto;
import com.example.springwebh2mybatis.model.PostKaryawanDto;
import com.example.springwebh2mybatis.model.PutKaryawanDto;
import com.example.springwebh2mybatis.service.KaryawanService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class KaryawanController {

  private final KaryawanService karyawanService;

  public KaryawanController(KaryawanService karyawanService) {
    this.karyawanService = karyawanService;
  }

  @GetMapping("/karyawan")
  public List<GetKaryawanDto> getKaryawan(@RequestBody GetKaryawanDto karyawan) {
    return karyawanService.getKaryawan(karyawan);
  }

  @PostMapping("/karyawan")
  public PostKaryawanDto postKaryawan(@Valid @RequestBody PostKaryawanDto karyawan) {
    return karyawanService.postKaryawan(karyawan) == 1 ? karyawan : null;
  }

  @PutMapping("/karyawan")
  public PutKaryawanDto putKaryawan(@Valid @RequestBody PutKaryawanDto karyawan) {
    return karyawanService.putKaryawan(karyawan) == 1 ? karyawan : null;
  }

  @DeleteMapping("/karyawan/{id:[a-zA-Z0-9]}")
  public String deleteKaryawan(@PathVariable("id") String id) {
    return karyawanService.deleteKaryawan(id) == 1 ? id : null;
  }
}
