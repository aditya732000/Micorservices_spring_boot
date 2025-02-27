import { createSlice } from "@reduxjs/toolkit";

const productSlice = createSlice({
    name: "product",
    initialState: {
        selectedProducts: []
    },
    reducers: {
        setSelectedProducts: (state, action) => {
            state.selectedProducts = action.payload;
        }
    }
});

export const { setSelectedProducts } = productSlice.actions;
export default productSlice.reducer;
