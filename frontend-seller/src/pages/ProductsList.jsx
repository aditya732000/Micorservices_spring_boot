import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { useListProductsQuery } from '../redux/api/productApi';

export default function ProductList() {
  const [products, setProducts] = useState([]);
  const {data, isLoading} = useListProductsQuery()
  if (isLoading) {
    return <p>Loading...</p>
  }

  console.log(data)



  return (
    <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-md rounded-xl">
      <h2 className="text-2xl font-bold mb-6">Product List</h2>
      {products.length > 0 ? (
        <ul>
          {products.map((product) => (
            <li key={product.id} className="border-b py-4">
              <h3 className="text-lg font-semibold">{product.name}</h3>
              <p className="text-gray-600">{product.description}</p>
              <p className="text-gray-800">Category: {product.category}</p>
              <p className="text-gray-800">Price: ${product.price}</p>
              <p className="text-gray-800">Stock: {product.stockQuantity}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p className="text-gray-600">No products available.</p>
      )}
    </div>
  );
}
