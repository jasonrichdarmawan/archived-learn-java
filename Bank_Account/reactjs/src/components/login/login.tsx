import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { loginAsync } from "../../features/authorization/authorizationSlice";
import styles from "./login.module.css";

export default function Login() {
  const [User_ID, setUser_ID] = useState<string>("");
  const [PIN, setPIN] = useState<number | string>("");

  const dispatch = useDispatch();

  function handleParsePIN(e: React.ChangeEvent<HTMLInputElement>) {
    if (isNaN(parseInt(e.target.value))) {
      setPIN("");
    } else {
      setPIN(parseInt(e.target.value));
    }
  }

  function handleLogin(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    e.stopPropagation();

    dispatch(loginAsync(User_ID, PIN));
  }

  return (
    <div className={styles.container}>
      <p className={styles.text}>Login</p>
      <form onSubmit={handleLogin}>
        <label>
          <p className={[styles.text, styles.colorblue, styles.underline].join(' ')}>Silahkan masukkan User ID Anda</p>
          <p className={[styles.text, styles.colororange].join(' ')}>Please enter Your User ID</p>
          <input
            type="text"
            value={User_ID}
            pattern="[a-zA-Z0-9]{12}"
            title="Minimum and Maximum User_ID length is 12 characters and does not have special characters"
            required
            onChange={(e) => setUser_ID(e.target.value)}
          />
        </label>
        <br />
        <br />
        <label>
          <p className={[styles.text, styles.colorblue, styles.underline].join(' ')}>
            Silahkan masukkan PIN Internet Banking Anda
          </p>
          <p className={[styles.text, styles.colororange].join(' ')}>Please enter Your Internet Banking PIN</p>
          <input
            type="password"
            value={PIN}
            pattern="[0-9]{6}"
            title="Minimum and Maximum PIN length is 6"
            required
            onChange={(e) => handleParsePIN(e)}
          />
        </label>
        <br />
        <br />
        <br />
        <input type="submit" />
      </form>
    </div>
  );
}
