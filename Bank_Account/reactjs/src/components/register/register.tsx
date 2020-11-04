import React from "react";
import { useDispatch } from "react-redux";
import "./register.css";

interface RegisterModel {
  PIN: number | string;
  Full_Name: string;
  Birth_Date: string;
  ISO_4217: number | string;
  Address_3: string;
  Address_4: string;
  Address_1: string;
  Address_2: string;
  Zip_Code: number | string;
  ISO_3166_1: number | string;
}

export function Register() {
  const [state, setState] = React.useState<RegisterModel>({
    PIN: "",
    Full_Name: "",
    Birth_Date: "",
    ISO_4217: "",
    Address_3: "",
    Address_4: "",
    Address_1: "",
    Address_2: "",
    Zip_Code: "",
    ISO_3166_1: "",
  });

  const dispatch = useDispatch();

  function handleChange(
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) {
    if (
      e.target.name === "PIN" ||
      e.target.name === "ISO_4217" ||
      e.target.name === "Zip_Code" ||
      e.target.name === "ISO_3166_1"
    ) {
      setState((prevState) => ({
        ...prevState,
        [e.target.name]: parseInt(e.target.value),
      }));
    } else {
      setState((prevState) => ({
        ...prevState,
        [e.target.name]: e.target.value,
      }));
    }
  }

  function handleSubmit(e: React.FormEvent<HTMLInputElement>) {
    e.preventDefault();
    console.log(state);
    // dispatch(loginAsync(User_ID, PIN));
  }

  return (
    <div>
      <p>Register</p>
      <form>
        <label>
          <p className="color-blue underline">
            Silahkan masukkan PIN Internet Banking Anda
          </p>
          <p className="color-orange">Please enter Your Internet Banking PIN</p>
          <input
            name="PIN"
            value={state.PIN}
            type="password"
            onChange={(e) => handleChange(e)}
          />
        </label>

        <br />

        <label>
          <p className="color-blue underline">
            Silahkan masukkan Nama Lengkap Anda
          </p>
          <p className="color-orange">Please enter Your Full Name</p>
          <input
            name="Full_Name"
            value={state.Full_Name}
            type="text"
            onChange={(e) => handleChange(e)}
          />
        </label>

        <br />

        <label>
          <p className="color-blue underline">
            Silahkan masukkan Tanggal Lahir Anda
          </p>
          <p className="color-orange">Please enter Your Birth Date</p>
          <input
            name="Birth_Date"
            value={state.Birth_Date}
            type="date"
            onChange={(e) => handleChange(e)}
          />
        </label>

        <br />

        <label>
          <p className="color-blue underline">Silahkan pilih Mata Uang Anda</p>
          <p className="color-orange">Please choose Your Currency</p>
          <select name="ISO_4217" onChange={(e) => handleChange(e)}>
            <option></option>
            <option value={360}>IDR</option>
          </select>
        </label>

        <br />

        <label>
          <p className="color-blue underline">
            Silahkan masukkan Kecamatan Anda
          </p>
          <p className="color-orange">Please enter Your District</p>
          <input
            name="Address_3"
            value={state.Address_3}
            type="text"
            onChange={(e) => handleChange(e)}
          />
        </label>

        <br />

        <label>
          <p className="color-blue underline">
            Silahkan masukkan Kelurahan Anda
          </p>
          <p className="color-orange">Please enter Your Sub-district</p>
          <input
            name="Address_4"
            value={state.Address_4}
            type="text"
            onChange={(e) => handleChange(e)}
          />
        </label>

        <br />

        <label>
          <p className="color-blue underline">Silahkan masukkan Alamat Anda</p>
          <p className="color-orange">Please enter Your Address</p>
          <input
            name="Address_1"
            value={state.Address_1}
            type="text"
            onChange={(e) => handleChange(e)}
          />
        </label>

        <br />

        <label>
          <p className="color-blue underline">Silahkan masukkan Kota Anda</p>
          <p className="color-orange">Please enter Your City</p>
          <input
            name="Address_2"
            value={state.Address_2}
            type="text"
            onChange={(e) => handleChange(e)}
          />
        </label>

        <br />

        <label>
          <p className="color-blue underline">
            Silahkan masukkan Kode Pos Anda
          </p>
          <p className="color-orange">Please enter Your Zip Code</p>
          <input
            name="Zip_Code"
            value={state.Zip_Code}
            type="number"
            onChange={(e) => handleChange(e)}
          />
        </label>

        <br />

        <label>
          <p className="color-blue underline">Silahkan masukkan Negara Anda</p>
          <p className="color-orange">Please enter Your Nationality</p>
          <select name="ISO_3166_1" onChange={(e) => handleChange(e)}>
            <option></option>
            <option value={360}>Indonesia</option>
          </select>
        </label>

        <br />
        <br />
        <br />
        <input type="submit" onClick={handleSubmit} />
      </form>
    </div>
  );
}
