import { useListProductsQuery } from '../redux/api/productApi';
import { Link } from 'react-router';

export default function ProductList() {
  const {data: products, isLoading} = useListProductsQuery()
  if (isLoading) {
    return <p>Loading...</p>
  }

  return (
    <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-md rounded-xl">
      <h2 className="text-2xl font-bold mb-6">Product List</h2>
      {products.length > 0 ? (
        <ul>
          {products.map((product) => (
            <Link to={`/product/${product.id}`} key={product.price} className="block border-b py-4">
              <h3 className="text-lg font-semibold">{product.name}</h3>
              <p className="text-gray-600">{product.description}</p>
              <p className="text-gray-800">Category: {product.category}</p>
              <p className="text-gray-800">Price: ${product.price}</p>
              <p className="text-gray-800">Stock: {product.quantity}</p>
            </Link>
          ))}
        </ul>
      ) : (
        <p className="text-gray-600">No products available.</p>
      )}
    </div>
  );
}
