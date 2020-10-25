package com.example.designpattern.repository;

import com.example.designpattern.model.StaffWorkerModel;

import java.util.List;

public interface StaffDao {
  //  1. Input Data Karyawan -> Masukan 2 lg Karyawan (Builder Design Pattern)
  int postStaff(StaffWorkerModel staff);

//  2. Edit Data Karyawan (Nama Karyawan)
//    -> -> Input ID atau Nama Karyawan
//  3. Hitung Total Gaji Karyawan
//    -> Satu Bulan 20 hari (GajiPokok + TunjMakan + TunjTransport)
//    -> Untuk seluruh Karyawan
//  4. Tampilkan Laporan per Karyawan
//	  -> Input ID atau Nama Karyawan
//    ID :
//    Nama :
//    Absensi :
//    Cuti :
//    Tanpa Kabar :
//    Total Gaji :
  StaffWorkerModel getStaffById(Integer id);

  List<StaffWorkerModel> getStaffsByName(String nama);

  int putStaffById(StaffWorkerModel staff);
  int putStaffByName(StaffWorkerModel staff);
  StaffWorkerModel deleteStaffById(int id);

//  5. Create File dan FTP ke Server -> LaporanKaryawan.txt (Adapter Design Pattern)-> Untuk FTP yang tadinya Socket
//
//	-> Output di File
//          ID,Nama,JmlAbsensi,JmlCutiTerpakai,JmlTanpaKabar,TotalGaji
//	1,John,21,1,0,XXX
//	2,Peter,19,1,2,XXX
//	3,------
//          4,------
//          5,------
//          6,------
  List<StaffWorkerModel> getStaffs();
}
