import React, { useEffect } from "react";
import { Redirect, Route, Switch } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import {
  login,
  logout,
  selectIsLoggedIn,
} from "./features/authorization/authorizationSlice";
import { Login } from "./components/login/login";
import { Register } from "./components/register/register";
import jwt_decode from "jwt-decode";

function App() {
  const isLoggedIn = useSelector(selectIsLoggedIn);
  const dispatch = useDispatch();

  useEffect(() => {
    // decode and check if token has expired.
    let token = sessionStorage.getItem("token");
    if (token != null) {
      const decoded: { exp: number } = jwt_decode(token);
      if (decoded.exp - Date.now() / 1000 > 0) {
        dispatch(login());
      } else {
        sessionStorage.removeItem("token");
      }

      // auto logout
      setTimeout(() => {
        dispatch(logout());
      }, decoded.exp * 1000 - Date.now());
    }
  }, [dispatch]);

  return (
    <Switch>
      {!isLoggedIn ? (
        <>
          <Switch>
            <Route exact path="/register">
              <Register />
            </Route>
            <Route exact path="/login">
              <Login />
            </Route>
            <Route path="*">
              <Redirect to="/login" />
            </Route>
          </Switch>
        </>
      ) : (
        <>
          <Route path="*">
            <Redirect to="/" />
          </Route>
        </>
      )}
    </Switch>
  );
}

export default App;
