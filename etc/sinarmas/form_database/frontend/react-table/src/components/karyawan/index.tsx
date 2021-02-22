import React from "react";
import { environment } from "../../environments/environment";

interface GetKaryawanDto {
  id: string;
  nama: string;
  alamat: string;
  rt: string;
  rw: string;
  kecamatan: string;
  kelurahan: string;
  telepon: string;
  input_date: string;
  input_by: string;
  approve_date: string;
  approve_by: string;
}

const KaryawanTable = () => {
  const [data, setData] = React.useState<GetKaryawanDto[]>([]);

  const fetchData = () => {
    fetch(`${environment.httpBaseUrl}/v1/karyawans`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({}),
    }).then(async (response) => {
      const body = await response.json();
      setData(body);
    });
  };

  const renderTableDate = () =>
    data.map((karyawan) => {
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

  React.useEffect(() => {
    console.log("KaryawanTable re-render");
    fetchData();
  }, []);

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
