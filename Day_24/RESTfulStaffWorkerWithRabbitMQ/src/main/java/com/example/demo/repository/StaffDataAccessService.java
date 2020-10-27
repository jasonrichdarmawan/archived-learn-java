package com.example.demo.repository;

import com.example.demo.model.StaffWorkerModel;
import com.example.demo.util.StringToArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * @deprecated use StaffMyBatis
 */
@Repository
public class StaffDataAccessService implements StaffDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<StaffWorkerModel> getStaffs() {
    return this.jdbcTemplate.query(
            "SELECT * FROM staff",
            (rs, rowNum) -> new StaffWorkerModel(
                    rs.getInt("IDKaryawan"),
                    rs.getString("Nama"),
                    rs.getBigDecimal("TunjanganPulsa"),
                    rs.getBigDecimal("GajiPokok"),
                    rs.getInt("AbsensiHari"),
                    StringToArrayList.toArrayList(rs.getString("Email"))
            )
    );
  }

  @Override
  public StaffWorkerModel getStaffById(int id) {
    return this.jdbcTemplate.queryForObject(
            "SELECT * FROM staff where IDKaryawan=?",
            new Object[]{id},
            (rs, rowNum) -> new StaffWorkerModel(
                    rs.getInt("IDKaryawan"),
                    rs.getString("Nama"),
                    rs.getBigDecimal("TunjanganPulsa"),
                    rs.getBigDecimal("GajiPokok"),
                    rs.getInt("AbsensiHari"),
                    StringToArrayList.toArrayList(rs.getString("Email"))
            )

    );
  }

  @Override
  public StaffWorkerModel postStaff(StaffWorkerModel staff) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
              PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO staff (Nama, TunjanganPulsa, GajiPokok, AbsensiHari, TunjanganMakan, Email) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
              preparedStatement.setString(1, staff.getNama());
              preparedStatement.setBigDecimal(2, staff.getTunjanganPulsa());
              preparedStatement.setBigDecimal(3, staff.getGajiPokok());
              preparedStatement.setInt(4, staff.getAbsensiHari());
              preparedStatement.setBigDecimal(5, staff.HitungTunjanganMakan());
              preparedStatement.setObject(6, staff.getEmail());
              return preparedStatement;
            }, keyHolder
    );
    staff.setIDKaryawan(keyHolder.getKey().intValue());
    return staff;
  }

  @Override
  public int putStaffById(StaffWorkerModel staff) {
    String Email = StringToArrayList.parseArrayList(staff.getEmail());
    return this.jdbcTemplate.update(
            "UPDATE staff SET Nama=coalesce(?,Nama), TunjanganPulsa=coalesce(?,TunjanganPulsa), GajiPokok=coalesce(?,GajiPokok), AbsensiHari=coalesce(?,AbsensiHari), TunjanganMakan=coalesce(?,TunjanganMakan), Email=coalesce(?,Email) WHERE IDKaryawan=?",
            staff.getNama(), staff.getTunjanganPulsa(), staff.getGajiPokok(), staff.getAbsensiHari(), staff.HitungTunjanganMakan(), Email, staff.getIDKaryawan()
    );
  }

  @Override
  public int deleteStaffById(int id) {
    return this.jdbcTemplate.update(
            "DELETE FROM staff WHERE IDKaryawan=?",
            id
    );
  }

  @Override
  public int deleteStaffs() {
    return this.jdbcTemplate.update(
      "DELETE FROM staff"
    );
  }
}
