import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { loginAsync } from "../../features/authorization/authorizationSlice";
import "./login.css";

export function Login() {
  const [User_ID, setUser_ID] = useState<string>("");
  const [PIN, setPIN] = useState<number | string>("");

  const dispatch = useDispatch();

  function handleLogin(e: React.FormEvent<HTMLInputElement>) {
    e.preventDefault();
    dispatch(loginAsync(User_ID, PIN));
  }

  return (
    <div>
      <p>Login</p>
      <form>
        <label>
          <p className="color-blue underline">Silahkan masukkan User ID Anda</p>
          <p className="color-orange">Please enter Your User ID</p>
          <input
            type="text"
            value={User_ID}
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
            onChange={(e) => setPIN(parseInt(e.target.value))}
          />
        </label>
        <br />
        <br />
        <br />
        <input type="submit" onClick={handleLogin} />
      </form>
    </div>
  );
}
