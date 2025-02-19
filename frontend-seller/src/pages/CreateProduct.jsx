import { useState } from 'react';
import { useCreateProductMutation } from '../redux/api/productApi';

export default function CreateProduct() {
  const [product, setProduct] = useState({
    name: '',
    description: '',
    category: '',
    price: '',
    stockQuantity: '',
  });

  const [createProduct] = useCreateProductMutation()

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await createProduct(product)
      console.log('Product Created:', response);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div className="mx-auto mt-10 p-6 bg-white shadow-md rounded-xl">
      <h2 className="text-2xl font-bold mb-6">Create Product</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-4">
          <label className="block text-gray-700">Name</label>
          <input 
            type="text" 
            name="name" 
            value={product.name} 
            onChange={handleChange} 
            className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Description</label>
          <textarea 
            name="description" 
            value={product.description} 
            onChange={handleChange} 
            className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Category</label>
          <input 
            type="text" 
            name="category" 
            value={product.category} 
            onChange={handleChange} 
            className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Price</label>
          <input 
            type="number" 
            name="price" 
            value={product.price} 
            onChange={handleChange} 
            className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Stock Quantity</label>
          <input 
            type="number" 
            name="stockQuantity" 
            value={product.stockQuantity} 
            onChange={handleChange} 
            className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none"
          />
        </div>
        <button 
          type="submit" 
          className="w-full bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 rounded-lg transition duration-300"
        >
          Create Product
        </button>
      </form>
    </div>
  );
}