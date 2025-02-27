import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router";

const ShippingInfo = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const selectedProducts = useSelector(state => state.product.selectedProducts);

    const [shippingDetails, setShippingDetails] = useState({
        fullName: "",
        address: "",
        city: "",
        state: "",
        zipCode: "",
        country: "",
    });

    const handleChange = (e) => {
        setShippingDetails({ ...shippingDetails, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Save shipping info in Redux

        // Prepare data to send to backend
        const orderData = {
            shippingInfo: shippingDetails,
            products: selectedProducts.map(product => ({
                productId: product.id,
                quantity: product.quantity,
                price: product.price
            }))
        };

        try {
            /*const response = await axios.post("http://localhost:8080/order", orderData, {
                headers: { "Content-Type": "application/json" }
            });

            if (response.status === 200) {
                alert("Order placed successfully!");
                navigate("/success"); // Redirect to success page
            }*/
        } catch (error) {
            console.error("Error placing order:", error.response ? error.response.data : error.message);
        }
    };

    return (
        <div>
            <h2>Shipping Information</h2>
            <form onSubmit={handleSubmit} className='block'>
                <input type="text" name="fullName" placeholder="Full Name" required onChange={handleChange} className="w-full" />
                <input type="text" name="address" placeholder="Address" required onChange={handleChange} className='w-full' />
                <input type="text" name="city" placeholder="City" required onChange={handleChange} />
                <input type="text" name="zipCode" placeholder="Zip Code" required onChange={handleChange} />
                <button type="submit">Place Order</button>
            </form>
        </div>
    );
};

export default ShippingInfo;
