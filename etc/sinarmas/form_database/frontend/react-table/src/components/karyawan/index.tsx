import React from "react";
import { environment } from "../../environments/environment";

const KaryawanTable = () => {
  const [data, setData] = React.useState([]);

  const fetchData = () => {
    fetch(`http://localhost:8080/v1/karyawan`, {
      method: "GET",
      body: JSON.stringify({}),
    }).then(async (response) => {
      const body = await response.text();
      console.log(body);
    });
  };

  fetchData();

  const renderTableDate = () =>
    [
      {
        id: "1",
        nama: "nama",
        alamat: "alamat",
        rt: "rt",
        rw: "rw",
        kecamatan: "kecamatan",
        kelurahan: "kelurahan",
        telepon: "telepon",
        input_date: "2020-02-22",
        input_by: "pic input",
        approve_date: "2020-02-22",
        approve_by: "pic approve",
      },
    ].map((karyawan) => {
      return (
        <tr key={karyawan.id}>
          <td>{karyawan.id}</td>
          <td>{karyawan.nama}</td>
          <td>{karyawan.alamat}</td>
          <td>{karyawan.rt}</td>
          <td>{karyawan.rw}</td>
          <td>{karyawan.kecamatan}</td>
          <td>{karyawan.kelurahan}</td>
          <td>{karyawan.telepon}</td>
          <td>{karyawan.input_date}</td>
          <td>{karyawan.input_by}</td>
          <td>{karyawan.approve_date}</td>
          <td>{karyawan.approve_by}</td>
        </tr>
      );
    });

  return (
    <table>
      <thead>
        <tr>
          <th>ID Karyawan</th>
          <th>Nama</th>
          <th>Alamat</th>
          <th>RT</th>
          <th>RW</th>
          <th>Kecamatan</th>
          <th>Kelurahan</th>
          <th>Telepon</th>
          <th>Input Date</th>
          <th>Input By</th>
          <th>Approve Date</th>
          <th>Approve By</th>
        </tr>
      </thead>
      <tbody>{renderTableDate()}</tbody>
    </table>
  );
};

export default KaryawanTable;
