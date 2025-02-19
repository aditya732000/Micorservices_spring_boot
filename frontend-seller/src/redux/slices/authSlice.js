import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  token: localStorage.getItem('access_token') || null,
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setCredentials: (state, action) => {
      state.token = action.payload;
      localStorage.setItem('access_token', action.payload);
    },
    logout: (state) => {
      state.token = null;
      localStorage.removeItem('access_token');
    },
  },
});

export const { setCredentials, logout } = authSlice.actions;
export default authSlice.reducer;

export const selectCurrentToken = (state) => state.auth.token;