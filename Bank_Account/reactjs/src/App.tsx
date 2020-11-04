import React from "react";
import { Route, Switch } from "react-router-dom";
import { useSelector } from "react-redux";
import { selectIsLoggedIn } from "./features/authorization/authorizationSlice";

function App() {
  const isLoggedIn = useSelector(selectIsLoggedIn);

  return (
    <Switch>
      {!isLoggedIn && (
        <>
          <Route exact path="/login"></Route>
        </>
      )}
    </Switch>
  );
}

export default App;
