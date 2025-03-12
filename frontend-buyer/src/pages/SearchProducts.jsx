import { useSearchParams } from "react-router";
import { useSearchProductsQuery } from "../redux/api/productApi";

export default function SearchProducts() {
  const [searchParams] = useSearchParams()
  const query = searchParams.get("query")
  const {data: products, isLoading} = useSearchProductsQuery(query)
  if (isLoading) {
    return <p className="text-center text-gray-500">Loading...</p>
  }

  console.log(products)

  return (
      <div className="container mx-auto p-6">
        <h1 className="text-3xl font-bold text-center mb-6">Results For {query}</h1>
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            {products.map((product) => (
              <div
                key={product.id}
                className="border rounded-lg shadow-lg p-4 bg-white hover:shadow-xl transition"
              >
                <img
                  src={product.image_url}
                  alt={product.name}
                  className="w-full h-48 object-cover rounded-md"
                />
                <h2 className="text-xl font-semibold mt-4">{product.name}</h2>
                <p className="text-gray-500 mt-2">{product.description}</p>
                <p className="text-lg font-bold mt-3 text-blue-500">
                  ${product.price}
                </p>
              </div>
            ))}
          </div>
      </div>
    );
}
