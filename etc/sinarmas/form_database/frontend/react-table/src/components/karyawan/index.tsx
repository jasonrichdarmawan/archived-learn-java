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
          <input type="text" name="nama" />
        </label>
        <label>
          Alamat
          <input type="text" name="alamat" />
        </label>
        <label>
          RT
          <input type="text" name="rt" />
        </label>
        <label>
          RW
          <input type="text" name="rw" />
        </label>
        <label>
          Kecamatan
          <input type="text" name="kecamatan" />
        </label>
        <label>
          Kelurahan
          <input type="text" name="kelurahan" />
        </label>
        <label>
          <input type="text" name="telepon" />
        </label>
        <label>
          <input type="text" name="input_date" />
        </label>
        <label>
          <input type="text" name="input_by" />
        </label>
        <label>
          <input type="text" name="approve_date" />
        </label>
        <label>
          <input type="text" name="approve_by" />
        </label>
      </form>
      <KaryawanTable query={query} />
    </>
  );
};

export default Karyawan;
