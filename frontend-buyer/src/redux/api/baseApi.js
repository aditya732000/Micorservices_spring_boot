import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

const baseQuery = fetchBaseQuery({
  baseUrl: "https://8083-aditya7812-ecommercemic-h7ba9h2xbki.ws-us118.gitpod.io/api/",
  credentials: "include",
  prepareHeaders: (headers, { getState }) => {
    const token = getState().auth.token;

    if (token) {
      headers.set("authorization", `Bearer ${token}`);
    }
    return headers;
  },
});

const baseQueryWithReauth = async (args, api, extraOptions) => {
  let result = await baseQuery(args, api, extraOptions);
  console.log(result)
  return result;
};

export const apiSlice = createApi({
  reducerPath: "baseApi",
  baseQuery: baseQueryWithReauth,
  //tagTypes: ["Course", "User", "Courses", "CourseProgress"],
  // eslint-disable-next-line no-unused-vars
  endpoints: (builder) => ({}),
});