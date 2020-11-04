import { createSlice } from "@reduxjs/toolkit";
import { RootState } from "../../app/store";

export const authorizationSlice = createSlice({
  name: "authorization",
  initialState: {
    isLoggedIn: false
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

export const selectIsLoggedIn = (state: RootState) => state.authorization.isLoggedIn;

export default authorizationSlice.reducer;
