import React, { useEffect } from "react";
import { Redirect, Route, Switch } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import {
  login,
  selectIsLoggedIn,
} from "./features/authorization/authorizationSlice";
import { Login } from "./components/login/login";
import { Register } from "./components/register/register";

function App() {
  const isLoggedIn = useSelector(selectIsLoggedIn);
  const dispatch = useDispatch();

  useEffect(() => {
    if (sessionStorage.getItem("token") != null) {
      dispatch(login());
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
