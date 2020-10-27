package com.siswarabbitmq.database.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

public class Siswa {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_siswa;

    private String nama, address, status;
    private Map<String, Float> nilai;

    public Siswa(){}

    public Siswa(Long id_siswa, String nama, String address, String status, Map<String, Float> nilai){
        super();
        this.id_siswa = id_siswa;
        this.nama = nama;
        this.address = address;
        this.status = status;
        this.nilai = nilai;
    }
//    public Siswa(String nama, String address, String status, Map<String, Float> nilai){
//        super();
//        this.id_siswa = (long) 0;
//        this.nama = nama;
//        this.address = address;
//        this.status = status;
//        this.nilai = nilai;
//    }

    public Long getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(Long id_siswa) {
        this.id_siswa = id_siswa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Float> getNilai() {
        return nilai;
    }

    public void setNilai(Map<String, Float> nilai) {
        this.nilai = nilai;
    }

//    @Override
//    public String toString()
//    {
//        float fisika = nilai.get("fisika");
//        float kalkulus = nilai.get("kalkulus");
//        float biologi = nilai.get("biologi");
//
//        return  "{" +
//                "id_siswa:" + this.id_siswa +
//                ", nama:" + this.nama +
//                ", address:" + this.address +
//                ", status:" + this.status +
//                ", nilai:{" +
//                    "fisika:" + fisika +
//                    ", kalkulus:" + kalkulus +
//                    ", biologi:" + biologi +
//                    "}"+
//                "}";
//    }

//    @Override
//    public String toString()
//    {
//        float fisika = nilai.get("fisika");
//        float kalkulus = nilai.get("kalkulus");
//        float biologi = nilai.get("biologi");
//
//        return  "{" +
//                "id_siswa:" + this.id_siswa +
//                ", nama:" + this.nama +
//                ", address:" + this.address +
//                ", status:" + this.status +
//                ", nilai:" + this.nilai +
//                "}";
//    }

//    @Override
//    public String toString()
//    {
//        float fisika = nilai.get("fisika");
//        float kalkulus = nilai.get("kalkulus");
//        float biologi = nilai.get("biologi");
//
//        return  "{" +
//                "id_siswa:" + this.id_siswa +
//                ", nama:" + this.nama +
//                ", address:" + this.address +
//                ", status:" + this.status +
//                ", nilai:" + this.nilai + "}";
//    }
}

