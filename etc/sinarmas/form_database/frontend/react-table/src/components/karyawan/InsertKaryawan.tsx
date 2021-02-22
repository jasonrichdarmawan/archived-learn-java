import React from "react";
import { environment } from "../../environments/environment";

export interface PostKaryawanDto {
  nama: string;
  alamat?: string;
  rt?: string;
  rw?: string;
  kecamatan?: string;
  kelurahan?: string;
  telepon?: string;
  input_date: string;
  input_by: string;
  approve_date?: string;
  approve_by?: string;
}

const InsertKaryawan = () => {
  const [form, setForm] = React.useState<PostKaryawanDto | {}>({});

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [event.target.name]: event.target.value || null });
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    fetch(`${environment.httpBaseUrl}/v1/karyawan`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    }).then(async (response) => {
      if (response.status === 200) {
        const body = await response.text();
        console.log(body);
      }
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Nama
        <input type="text" name="nama" onChange={handleChange} required />
      </label>
      <label>
        Alamat
        <input type="text" name="alamat" onChange={handleChange} />
      </label>
      <label>
        RT
        <input type="text" name="rt" onChange={handleChange} />
      </label>
      <label>
        RW
        <input type="text" name="rw" onChange={handleChange} />
      </label>
      <label>
        Kecamatan
        <input type="text" name="kecamatan" onChange={handleChange} />
      </label>
      <label>
        Kelurahan
        <input type="text" name="kelurahan" onChange={handleChange} />
      </label>
      <label>
        Telepon
        <input type="text" name="telepon" onChange={handleChange} />
      </label>
      <label>
        Input Date
        <input type="date" name="input_date" onChange={handleChange} required />
      </label>
      <label>
        Input By
        <input type="text" name="input_by" onChange={handleChange} required />
      </label>
      <label>
        Approve Date
        <input type="text" name="approve_date" onChange={handleChange} />
      </label>
      <label>
        Approve By
        <input type="text" name="approve_by" onChange={handleChange} />
      </label>
      <input type="submit" value="Submit" />
    </form>
  );
};

export default InsertKaryawan;
