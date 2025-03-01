import { useDispatch } from "react-redux"
import { useListCartItemsQuery } from "../redux/api/cartApi"
import { useCheckStockAvailabilityMutation } from "../redux/api/productApi"
import CartItem from "./CartItem"
import { useNavigate } from "react-router"
import { setSelectedProducts } from "../redux/slices/productSlice"


export default function Cart() {
    const dispatch = useDispatch()
    const navigate = useNavigate()
    const {data : cart, isLoading} = useListCartItemsQuery()
    const [checkStockAvailability] = useCheckStockAvailabilityMutation()
    if (isLoading) {
        return <p>Loading...</p>
    }

    const checkAvailability = async () => {
      const stockRequest = {
        products: cart.map(product => ({
            productId: product.productId,
            quantity: product.quantity
        }))
      }
      const response = await checkStockAvailability(stockRequest)
      console.log(response)
      if (response.data == true) {
        dispatch(setSelectedProducts(cart));
        navigate("/address-info")
      } else {
        console.log("Some Products are - OUT OF STOCK")
      }
      
    }
    return (
       <>
       {cart?.length > 0 ? (
        <>
        <ul>
          {cart.map((cartItem) => (
            <CartItem key={cartItem.productId} cartItem={cartItem} />
          ))}
        </ul>
        <button onClick={checkAvailability} className="cursor-pointer bg-blue-400">
          Checkout
        </button>
        </>
      ) : (
        <p className="text-gray-600">No products available.</p>
      )}
       </>
    )
}
