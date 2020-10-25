package com.example.review.controller;

import com.example.review.model.StudentModel;

import java.util.List;


public interface StudentRESTInterface {

  // SDR 1. 1. Input
  int postStudent(StudentModel student);

//  int postGrades(StudentModel studentModel);

  // SDR 1. 2. Edit
  int putStudent(StudentModel student);

  // SDR 1. 3. Delete
  int deleteStudent(int id);

  // SDR 1. 4. LIST Semua Mahasiswa (Tanpa Nilai)
  // SDR 4. Laporan seluruh Siswa
  //  	ID :
  //	  NAMA :
  //	  AVERAGE :
  //	  (Hitung Nilai Semua Siswa Average -> 5-6 = D, 6-7=C, 7-8=B, >8 = A)
  List<StudentModel> getStudents();

  // SDR 2. Absensi Siswa Terdaftar
  // SDR 3. Masukan Nilai Siswa -> input ID Siswa
  // SDR 5. Laporan per Siswa. by ID or Nama
  StudentModel getStudentById();

  // SDR 5. Laporan per Siswa. by ID or Nama
  List<StudentModel> getStudentByName();
}
