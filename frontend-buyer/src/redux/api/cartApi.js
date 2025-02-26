import { apiSlice } from "./baseApi";
export const cartApi = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    ListCartItems: builder.query({
      query: () => ({
        url: "cart/get",
        transformResponse: (response, meta, arg) => response.data,
      }),
    }),
    addCartItem: builder.mutation({
      query:(data) => ({
        url: "cart/add",
        method: "POST",
        body: data,
        transformResponse: (response, meta, arg) => response.data,

      })
    }),
    removeCartItem: builder.mutation({
        query: (id) => ({
            url: `cart/${id}`,
            method: "DELETE"
        })
    }),

    clearCart: builder.mutation({
        query: () => ({
            url: `cart/clear`,
            method: "DELETE"
        })
    })
  }),
});

export const {
  useAddCartItemMutation,
  useClearCartMutation,
  useRemoveCartItemMutation,
  useListCartItemsQuery
} = cartApi;