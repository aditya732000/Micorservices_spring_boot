import { apiSlice } from "./baseApi";
export const paymentApi = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    createCheckoutSession: builder.mutation({
      query: (orderItems) => ({
        url: "payment/create-checkout-session",
        method: "POST",
        body: orderItems,
      })

    })
  }),
});

export const {
  useCreateCheckoutSessionMutation
} = paymentApi;