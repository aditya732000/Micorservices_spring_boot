import { useSelector } from "react-redux";
import { useCreateCheckoutSessionMutation } from "../redux/api/paymentApi";

const Checkout = () => {
    const selectedProducts = useSelector(state => state.product.selectedProducts);
    const [createCheckoutSession] = useCreateCheckoutSessionMutation()
    const orderItems =  selectedProducts.map(product => ({
        productId: product.productId,
        quantity: product.quantity,
        price: product.price
    }))
    const handleCheckout = async () => {
        try {
            const response = await createCheckoutSession(orderItems)

            if (response.data.url) {
                window.location.href = response.data.url;
            }
        } catch (error) {
            console.error("Error creating checkout session:", error);
        }
    };

    return <button onClick={handleCheckout}>Checkout</button>;
};

export default Checkout;
