import { useListCartItemsQuery } from "../redux/api/cartApi"
import CartItem from "./CartItem"


export default function Cart() {
    const {data : cart, isLoading} = useListCartItemsQuery()
    if (isLoading) {
        return <p>Loading...</p>
    }
    console.log(cart[0])
    return (
       <>
       {cart.length > 0 ? (
        <ul>
          {cart.map((cartItem) => (
            <CartItem key={cartItem.productId} cartItem={cartItem} />
          ))}
        </ul>
      ) : (
        <p className="text-gray-600">No products available.</p>
      )}
       </>
    )
}
