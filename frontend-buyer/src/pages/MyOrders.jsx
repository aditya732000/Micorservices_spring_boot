import { useMyOrdersQuery } from "../redux/api/orderApi";

const MyOrders = () => {
    const { data: orders, error, isLoading } = useMyOrdersQuery();

    if (isLoading) return <div className="text-center mt-10">Loading orders...</div>;
    if (error) return <div className="text-center mt-10 text-red-500">Error fetching orders</div>;

    return (
        <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-lg rounded-lg">
            <h2 className="text-2xl font-semibold text-gray-700 mb-4">My Orders</h2>
            {orders?.length > 0 ? (
                <div className="space-y-4">
                    {orders.map((order) => (
                        <div key={order.id} className="border p-4 rounded-md shadow">
                            <h3 className="font-semibold">Order ID: {order.id}</h3>
                            <p className="text-sm text-gray-600">Date: {new Date(order.createdAt).toLocaleDateString()}</p>
                            <span>{order.name} (x{order.quantity})</span>
                            <span className="font-medium">${order.price.toFixed(2)}</span>

                            <p className="font-semibold mt-2">Total: ${order.totalPrice.toFixed(2)}</p>
                        </div>
                    ))}
                </div>
            ) : (
                <p className="text-gray-600 text-center">You have no orders yet.</p>
            )}
        </div>
    );
};

export default MyOrders;
