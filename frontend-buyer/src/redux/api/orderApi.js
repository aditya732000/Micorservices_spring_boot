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
    

    myOrders: builder.query({
      query: () => ({
        url: "order/my-orders",
        transformResponse: (response, meta, arg) => response.data,
      })
    })

  }),
});

export const {
    useCreateOrderMutation,
    useMyOrdersQuery
} = orderApi;