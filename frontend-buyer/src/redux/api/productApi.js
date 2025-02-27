import { apiSlice } from "./baseApi";
export const productApi = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    listProducts: builder.query({
      query: () => ({
        url: "product",
        transformResponse: (response, meta, arg) => response.data,
      }),
    }),
    productDetails: builder.query({
      query:(productId) => ({
        url: `product/${productId}`,
        transformResponse: (response, meta, arg) => response.data,

      })
    }),

    checkStockAvailability: builder.mutation({
      query: (stockRequest) => ({
        url: "product/check-stock",
        method: "POST",
        body: stockRequest,
        transformResponse: (response, meta, arg) => response.data,
      })

    })
  }),
});

export const {
  useListProductsQuery,
  useProductDetailsQuery,
  useCheckStockAvailabilityMutation
} = productApi;