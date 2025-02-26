import { useParams } from 'react-router';
import { useProductDetailsQuery } from '../redux/api/productApi';
import { useAddCartItemMutation } from '../redux/api/cartApi';

export default function ProductDetails() {
  const { productId } = useParams();
  const {data:product , isLoading} = useProductDetailsQuery(productId)
  const [addItemToCart] = useAddCartItemMutation()
  if (isLoading) {
    return <p>Loading...</p>
  }

  if (!product) {
    return <p>Nothing to see here...</p>
  }

  const handleAddToCart =async () => {
    const response = await addItemToCart({productId: product?.id, quantity:1, price: product.price})
    console.log(response)

  }

  return (
    <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-md rounded-xl">
      <h2 className="text-2xl font-bold mb-6">{product.name}</h2>
      <p className="text-gray-600 mb-4">{product.description}</p>
      <p className="text-gray-800 mb-2">Category: {product.category}</p>
      <p className="text-gray-800 mb-2">Price: ${product.price}</p>
      <p className="text-gray-800 mb-2">Stock: {product.stockQuantity}</p>
      <div>
      <button 
          onClick={handleAddToCart} 
          className="mt-4 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700"
        >
          Add to Cart
        </button>
      </div>
        
    </div>
  );
}
