package com.example.designpattern.repository;

import com.example.designpattern.builder.StaffBuilder;
import com.example.designpattern.model.StaffWorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StaffDataAccessRepository implements StaffDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int postStaff(StaffWorkerModel staff) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
              PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO staff (nama, gajiPokok, absensi, izin) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
              preparedStatement.setString(1, staff.getNama());
              preparedStatement.setBigDecimal(2, staff.getGajiPokok());
              preparedStatement.setObject(3, staff.getAbsensi());
              preparedStatement.setObject(4, staff.getIzin());
              return preparedStatement;
            }, keyHolder
    );
    return keyHolder.getKey().intValue();
  }

  @Override
  public StaffWorkerModel getStaffById(Integer id) {
    return jdbcTemplate.queryForObject(
            "SELECT * FROM staff WHERE id=?",
            new Object[]{id},
            (rs, rowNum) -> new StaffBuilder()
                    .setID(rs.getString("id"))
                    .setNama(rs.getString("nama"))
                    .setGajiPokok(rs.getString("gajiPokok"))
                    .setAbsensi(rs.getString("absensi"))
                    .setIzin(rs.getString("izin"))
                    .build()
    );
  }

  @Override
  public List<StaffWorkerModel> getStaffsByName(String nama) {
    return jdbcTemplate.query(
            "SELECT * FROM staff WHERE nama=?",
            new Object[]{nama},
            (rs, rowNum) -> new StaffBuilder()
                    .setID(rs.getString("id"))
                    .setNama(rs.getString("nama"))
                    .setGajiPokok(rs.getString("gajiPokok"))
                    .setAbsensi(rs.getString("absensi"))
                    .setIzin(rs.getString("izin"))
                    .build()
    );
  }

  @Override
  public int putStaffById(StaffWorkerModel staff) {
    return jdbcTemplate.update(
            "UPDATE staff SET nama=coalesce(?,nama), gajiPokok=coalesce(?,gajiPokok), absensi=coalesce(?,absensi), izin=coalesce(?,izin) WHERE id=?",
            staff.getNama(), staff.getGajiPokok(), staff.getAbsensi(), staff.getIzin(), staff.getID()
    );
  }

  @Override
  public int putStaffByName(StaffWorkerModel staff) {
    return jdbcTemplate.update(
            "UPDATE staff SET gajiPokok=coalesce(?,gajiPokok), absensi=coalesce(?,absensi), izin=coalesce(?,izin) WHERE nama=?",
            staff.getGajiPokok(), staff.getAbsensi(), staff.getIzin(), staff.getNama()
    );
  }

  @Override
  public StaffWorkerModel deleteStaffById(int id) {
    return null;
  }

  @Override
  public List<StaffWorkerModel> getStaffs() {
    return jdbcTemplate.query(
            "SELECT * FROM staff",
            (rs, rowNum) -> new StaffBuilder()
                    .setID(rs.getString("id"))
                    .setNama(rs.getString("nama"))
                    .setGajiPokok(rs.getString("gajiPokok"))
                    .setAbsensi(rs.getString("absensi"))
                    .setIzin(rs.getString("izin"))
                    .build()
    );
  }
}
