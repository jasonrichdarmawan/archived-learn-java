import React, { lazy, Suspense, useEffect } from "react";
import { Redirect, Route, Switch } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import {
  login,
  logout,
  selectIsLoggedIn,
} from "./features/authorization/authorizationSlice";
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
        sessionStorage.clear();
      }

      // auto logout
      setTimeout(() => {
        dispatch(logout());
      }, decoded.exp * 1000 - Date.now());
    }
  }, [dispatch]);

  function withSuspense(
    WrappedComponent: React.LazyExoticComponent<() => JSX.Element>
  ) {
    return (
      <Suspense fallback={<div>Loading...</div>}>
        <WrappedComponent />
      </Suspense>
    );
  }

  const Register = lazy(() => import("./components/register/register"));
  const Login = lazy(() => import("./components/login/login"));

  const Home = lazy(() => import("./components/home/home"));

  return (
    <Switch>
      {!isLoggedIn ? (
        <>
          <Switch>
            <Route
              exact
              path="/register"
              render={() => withSuspense(Register)}
            />
            <Route exact path="/login" render={() => withSuspense(Login)} />
            <Route path="*">
              <Redirect to="/login" />
            </Route>
          </Switch>
        </>
      ) : (
        <>
          <Route exact path="/" render={() => withSuspense(Home)} />
          <Route path="*">
            <Redirect to="/" />
          </Route>
        </>
      )}
    </Switch>
  );
}

export default App;
