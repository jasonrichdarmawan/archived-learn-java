import { createSlice } from "@reduxjs/toolkit";
import { AppDispatch, RootState } from "../../app/store";

export const authorizationSlice = createSlice({
  name: "authorization",
  initialState: {
    isLoggedIn: false,
  },
  reducers: {
    login: (state) => {
      state.isLoggedIn = true;
    },
    logout: (state) => {
      state.isLoggedIn = false;
    },
  },
});

export const { login, logout } = authorizationSlice.actions;

export const loginAsync = (User_ID: string, PIN: number | string) => async (
  dispatch: AppDispatch
) => {
  const response = await fetch("http://localhost:8080/api/v1/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      User_ID,
      PIN,
    }),
  });
  await response.json().then((data) => {
    if (data.message_code === 200) {
      sessionStorage.setItem("token", `Bearer + data.token`);
      dispatch(login());
    } else if (data.messagecode === 404) {
      // TODO
    }
  });
};

export const selectIsLoggedIn = (state: RootState) =>
  state.authorization.isLoggedIn;

export default authorizationSlice.reducer;
