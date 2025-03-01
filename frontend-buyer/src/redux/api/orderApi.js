import { apiSlice } from "./baseApi";

export const orderApi = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    createOrder: builder.mutation({
      query: (orderData) => ({
        url: "order/create",
        transformResponse: (response, meta, arg) => response.data,
        method: "POST",
        body: orderData,
      }),
    }),
    
    removeCartItem: builder.mutation({
        query: (id) => ({
            url: `cart/${id}`,
            method: "DELETE"
        })
    }),

  }),
});

export const {
    useCreateOrderMutation
} = orderApi;