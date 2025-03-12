import { useState } from "react";
import { useSelector } from "react-redux";
import { useCreateOrderMutation } from "../redux/api/orderApi";
import { loadStripe } from "@stripe/stripe-js";
import { useCreateCheckoutSessionMutation } from "../redux/api/paymentApi";

const ShippingInfo = () => {
    const selectedProducts = useSelector(state => state.product.selectedProducts);
    const [createCheckoutSession] = useCreateCheckoutSessionMutation();
    const [shippingDetails, setShippingDetails] = useState({
        fullName: "",
        address: "",
        city: "",
        zipCode: "",
    });

    const [createOrder] = useCreateOrderMutation();

    const handleChange = (e) => {
        setShippingDetails({ ...shippingDetails, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const orderData = {
            shippingInfo: shippingDetails,
            orderItems: selectedProducts.map(product => ({
                productId: product.productId,
                name: product.name,
                quantity: product.quantity,
                price: product.price
            }))
        };

        try {
            const response = await createOrder(orderData).unwrap();
            const orderItems = response.map(order => ({
                orderId: order.id,
                productId: order.productId,
                name: order.name,
                quantity: order.quantity,
                price: order.price
            }));

            const stripe = await loadStripe("pk_test_51OPRpGSFQ7VcCJN2PjmgWArUMwY2PDIZz77RYfBV8NLRKou5XTr5gY7wLTrBoZQAa4tKPjNdNJcc5YBSU2XhtmGP00qgPaNuCK");
            const checkoutResponse = await createCheckoutSession(orderItems).unwrap();

            await stripe.redirectToCheckout({ sessionId: checkoutResponse.id });
        } catch (error) {
            console.error("Error placing order:", error.response ? error.response.data : error.message);
        }
    };

    return (
        <div className="max-w-lg mx-auto mt-10 p-6 bg-white shadow-lg rounded-lg">
            <h2 className="text-2xl font-semibold text-gray-700 mb-4">Shipping Information</h2>
            <form onSubmit={handleSubmit} className='space-y-4'>
                <input type="text" name="fullName" placeholder="Full Name" required onChange={handleChange} className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400" />
                <input type="text" name="address" placeholder="Address" required onChange={handleChange} className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400" />
                <input type="text" name="city" placeholder="City" required onChange={handleChange} className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400" />
                <input type="text" name="zipCode" placeholder="Zip Code" required onChange={handleChange} className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-400" />
                <button type="submit" className="w-full bg-blue-500 hover:bg-blue-600 text-white py-2 rounded-md transition duration-300 cursor-pointer">Place Order</button>
            </form>
        </div>
    );
};

export default ShippingInfo;
