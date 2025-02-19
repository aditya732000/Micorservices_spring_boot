import { apiSlice } from "./baseApi";
export const productApi = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    createProduct: builder.mutation({
      query: (data) => ({
        url: "product/create",
        method: "POST",
        body: data,
      }),
    }),
    listProducts: builder.query({
      query: () => ({
        url: "products",
        transformResponse: (response, meta, arg) => response.data,
      }),
    }),
  }),
});

export const {
  useCreateProductMutation,
  useListProductsQuery
} = productApi;