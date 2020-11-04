import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { loginAsync } from "../../features/authorization/authorizationSlice";
import "./login.css";

export function Login() {
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
    <div>
      <p>Login</p>
      <form onSubmit={handleLogin}>
        <label>
          <p className="color-blue underline">Silahkan masukkan User ID Anda</p>
          <p className="color-orange">Please enter Your User ID</p>
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
          <p className="color-blue underline">
            Silahkan masukkan PIN Internet Banking Anda
          </p>
          <p className="color-orange">Please enter Your Internet Banking PIN</p>
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
