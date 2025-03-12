import { apiSlice } from "./baseApi";
export const productApi = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    createProduct: builder.mutation({
      query: (data) => ({
        url: "product/create",
        method: "POST",
        body: data,
        formData: true
      }),
    }),
    listProducts: builder.query({
      query: () => ({
        url: "product/seller",
        transformResponse: (response, meta, arg) => response.data,
      }),
    }),
    productDetails: builder.query({
      query:(productId) => ({
        url: `product/${productId}`,
        transformResponse: (response, meta, arg) => response.data,

      })
    })
  }),
});

export const {
  useCreateProductMutation,
  useListProductsQuery,
  useProductDetailsQuery
} = productApi;