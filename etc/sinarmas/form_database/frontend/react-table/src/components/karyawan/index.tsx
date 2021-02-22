import React from "react";
import KaryawanTable, { GetKaryawanDto } from "./KaryawanTable";

const Karyawan = () => {
  const [temp, setTemp] = React.useState<GetKaryawanDto | {}>({});
  const [query, setQuery] = React.useState<GetKaryawanDto | {}>({});

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTemp({ ...query, [event.target.name]: event.target.value || null });
  };

  React.useEffect(() => {
    console.log("Karyawan re-render");
    const timeout = setTimeout(() => {
      setQuery(temp);
    }, 1000);
    return () => clearTimeout(timeout);
  }, [temp]);

  return (
    <>
      <form>
        <label>
          ID Karyawan
          <input type="text" name="id" onChange={handleChange} />
        </label>
        <label>
          Nama
          <input type="text" name="nama" onChange={handleChange} />
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
          <input type="text" name="telepon" onChange={handleChange} />
        </label>
        <label>
          <input type="text" name="input_date" onChange={handleChange} />
        </label>
        <label>
          <input type="text" name="input_by" onChange={handleChange} />
        </label>
        <label>
          <input type="text" name="approve_date" onChange={handleChange} />
        </label>
        <label>
          <input type="text" name="approve_by" onChange={handleChange} />
        </label>
      </form>
      <KaryawanTable query={query} />
    </>
  );
};

export default Karyawan;
