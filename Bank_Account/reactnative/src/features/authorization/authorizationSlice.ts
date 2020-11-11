import {createSlice, PayloadAction} from '@reduxjs/toolkit';
import {AppDispatch, RootState} from '../../app/store';
import jwt_decode from 'jwt-decode';
import {LoginResponse} from './LoginResponse.types';
import {token} from './token.types';
import {sessionStorage} from './sessionStorage.types';
import Config from 'react-native-config';

const initialState: sessionStorage = {
  isLoggedIn: false,
  token: '',
  Full_Name: '',
  ISO_4217: '',
};

export const authorizationSlice = createSlice({
  name: 'authorization',
  initialState,
  reducers: {
    login: (state: sessionStorage, action: PayloadAction<sessionStorage>) => {
      state.isLoggedIn = action.payload.isLoggedIn;
      state.token = action.payload.token;
      state.Full_Name = action.payload.Full_Name;
      state.ISO_4217 = action.payload.ISO_4217;
    },
    logout: (state = initialState) => state,
  },
});

export const {login, logout} = authorizationSlice.actions;

export const loginAsync = (User_ID: string, PIN: number | string) => async (
  dispatch: AppDispatch,
) => {
  const response = await fetch(`${Config.baseUrl}/api/v1/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      User_ID,
      PIN,
    }),
  });
  await response.json().then((data: LoginResponse) => {
    if (data.message_code === 200) {
      const decoded = jwt_decode(data.token) as token;
      dispatch(
        login({
          isLoggedIn: true,
          token: data.token,
          Full_Name: data.Full_Name,
          ISO_4217: data.ISO_4217,
          Account_Number: decoded.Account_Number,
        }),
      );
    } else if (data.message_code === 404) {
      // TODO
    }
  });
};

export const selectIsLoggedIn = (state: RootState) =>
  state.authorization.Full_Name;

export default authorizationSlice.reducer;
