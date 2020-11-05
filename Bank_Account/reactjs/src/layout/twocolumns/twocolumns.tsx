import React from "react";
import { useDispatch } from "react-redux";
import { NavLink } from "react-router-dom";
import { logout } from "../../features/authorization/authorizationSlice";
import styles from "./twocolumns.module.css";


interface ChildrenProps {
  children: JSX.Element
}

export default function TwoColumns(props: ChildrenProps): JSX.Element {
  const ISO_4217 = sessionStorage.getItem("ISO_4217");
  const dispatch = useDispatch();

  const lang = {
    ID: {
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
          {props.children}
        </div>
      </div>
    </div>
  );
}
