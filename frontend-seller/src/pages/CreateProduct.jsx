import { useState } from 'react';
import { useCreateProductMutation } from '../redux/api/productApi';
import { Upload } from "lucide-react";

export default function CreateProduct() {

  const [product, setProduct] = useState({
    name: '',
    description: '',
    category: '',
    price: '',
    quantity: '',
    image: null
  });

  const [createProduct] = useCreateProductMutation()

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setProduct({ ...product, image: file });
};


  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      formData.append("name", product.name);
      formData.append("description", product.description);
      formData.append("price", product.price);
      formData.append("category", product.category);
      formData.append("quantity", product.quantity);
      formData.append("image", product.image);
      const response = await createProduct(formData);
      console.log('Product Created:', response.data);
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
            name="quantity" 
            value={product.quantity} 
            onChange={handleChange} 
            className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 focus:outline-none"
          />
        </div>
        <div className="mb-4">
          <input 
            type="file" 
            name="image"
            accept='image/*' 
            onChange={handleFileChange}
            id="image-upload" 
            className="hidden"
          />
          <label
            htmlFor="image-upload"
            className="flex items-center space-x-2 px-4 py-2 bg-blue-400 text-white rounded-lg cursor-pointer hover:bg-blue-700 transition"
          >
            <Upload className="w-5 h-5" />
            <span>Upload Image</span>
          </label>
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