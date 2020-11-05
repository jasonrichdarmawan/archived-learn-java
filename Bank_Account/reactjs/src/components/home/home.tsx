import React from "react";
import { useDispatch } from "react-redux";
import { NavLink } from "react-router-dom";
import { idText } from "typescript";
import { logout } from "../../features/authorization/authorizationSlice";
import styles from "./home.module.css";

export default function Home() {
  const Full_Name = sessionStorage.getItem("Full_Name");
  const ISO_4217 = sessionStorage.getItem("ISO_4217");
  const dispatch = useDispatch();

  const lang = {
    ID: {
      Intro: "Selamat Datang Di Internet Banking",
      History: "Histori Transaksi",
      Transfer: "Transfer Dana",
      Balance: "Informasi Saldo",
    },
  };

  return (
    <div className={styles.container}>
      <div>
        <button className={styles.mright} onClick={() => dispatch(logout())}>
          Logout
        </button>
      </div>
      <div className={styles.section}>
        <div className={styles.nav}>
          <ul>
            <li>
              <NavLink to="/history">
                {ISO_4217 === "360" && lang.ID.History}
              </NavLink>
            </li>
            <li>
              <NavLink to="/transfer">
                {ISO_4217 === "360" && lang.ID.Transfer}
              </NavLink>
            </li>
            <li>
              <NavLink to="/balance">
                {ISO_4217 === "360" && lang.ID.Balance}
              </NavLink>
            </li>
          </ul>
        </div>
        <div className={styles.article}>
          <p className={styles.text}>
            {Full_Name}, {ISO_4217 === "360" && lang.ID.Intro}
          </p>
        </div>
      </div>
    </div>
  );
}
