import { useParams } from 'react-router';
import { useProductDetailsQuery } from '../redux/api/productApi';

export default function ProductDetails() {
  const { productId } = useParams();
  const {data:product , isLoading} = useProductDetailsQuery(productId)
  if (isLoading) {
    return <p>Loading...</p>
  }


  return (
    <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-md rounded-xl">
      <h2 className="text-2xl font-bold mb-6">{product.name}</h2>
      <p className="text-gray-600 mb-4">{product.description}</p>
      <p className="text-gray-800 mb-2">Category: {product.category}</p>
      <p className="text-gray-800 mb-2">Price: ${product.price}</p>
      <p className="text-gray-800 mb-2">Stock: {product.quantity}</p>
    </div>
  );
}
