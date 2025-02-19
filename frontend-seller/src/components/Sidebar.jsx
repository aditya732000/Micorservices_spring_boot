import { Link } from 'react-router';

export default function Sidebar() {
  return (
    <div className="w-64 h-screen bg-gray-800 text-white flex flex-col p-4">
      <h2 className="text-2xl font-semibold mb-6">Admin Panel</h2>
      <nav className="space-y-4">
        <Link to="/dashboard" className="block py-2 px-4 rounded-lg hover:bg-gray-700">Dashboard</Link>
        <Link to="/orders" className="block py-2 px-4 rounded-lg hover:bg-gray-700">All Orders</Link>
        <Link to="/products" className="block py-2 px-4 rounded-lg hover:bg-gray-700">All Products</Link>
        <Link to="/create-product" className="block py-2 px-4 rounded-lg hover:bg-gray-700">Create Product</Link>
      </nav>
    </div>
  );
}
