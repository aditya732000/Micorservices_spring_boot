import { configureStore } from '@reduxjs/toolkit';
import { apiSlice } from '../api/baseApi';
import authReducer from '../slices/authSlice'
import productReducer from '../slices/productSlice'

export const store = configureStore({
  reducer: {
    [apiSlice.reducerPath] : apiSlice.reducer,
    auth: authReducer,
    product: productReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(apiSlice.middleware),
});