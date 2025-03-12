import { useParams } from 'react-router';
import { useProductDetailsQuery } from '../redux/api/productApi';
import { useAddCartItemMutation } from '../redux/api/cartApi';
import { useState } from 'react';

export default function ProductDetails() {
  const { productId } = useParams();
  const {data:product , isLoading} = useProductDetailsQuery(productId)
  const [loading, setLoading] = useState(false)
  const [addItemToCart] = useAddCartItemMutation()
  if (isLoading) {
    return <p>Loading...</p>
  }

  if (!product) {
    return <p>Nothing to see here...</p>
  }

  const handleAddToCart =async () => {
    setLoading(true)
    const response = await addItemToCart({productId: product.id, quantity:1, price: product.price, sellerId: product.sellerId, name: product.name})
    console.log(response)
    setLoading(false)

  }

  return (
    <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-md rounded-xl flex justify-around">
      <div>
      <img
        src={product.image_url}
        alt={product.name}
        className="w-full h-48 object-cover rounded-md"
      />
      </div>
      <div>
      <h2 className="text-2xl font-bold mb-6">{product.name}</h2>
      <p className="text-gray-600 mb-4">{product.description}</p>
      <p className="text-gray-800 mb-2">Category: {product.category}</p>
      <p className="text-gray-800 mb-2">Price: ${product.price}</p>
      <p className="text-gray-800 mb-2">Stock: {product.quantity}</p>
      <div>
      <button 
          onClick={handleAddToCart} 
          className="mt-4 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 cursor-pointer disabled:bg-blue-400 disabled:cursor-progress"
          disabled={loading}
        >
          Add to Cart
        </button>
        </div>
      </div>
        
    </div>
  );
}
