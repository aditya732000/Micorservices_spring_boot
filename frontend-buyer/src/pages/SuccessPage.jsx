import { useEffect } from "react";
import { CheckCircleIcon } from "lucide-react";
import { useNavigate } from "react-router";

export default function SuccessPage() {
  const navigate = useNavigate();

  useEffect(() => {
    const timeout = setTimeout(() => {
      navigate("/");
    }, 5000);

    return () => clearTimeout(timeout);
  }, [navigate]);

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white shadow-lg rounded-2xl p-8 max-w-md text-center">
        <CheckCircleIcon className="text-green-500 w-20 h-20 mx-auto mb-4" />
        <h1 className="text-2xl font-semibold text-gray-800">Payment Successful!</h1>
        <p className="text-gray-600 mt-2">Thank you for your purchase. Your order has been confirmed.</p>
        <p className="text-gray-600 mt-1">You will be redirected to the homepage shortly.</p>
        
        <button 
          onClick={() => navigate("/")}
          className="mt-6 px-6 py-2 text-white bg-green-500 hover:bg-green-600 rounded-lg transition"
        >
          Go to Homepage
        </button>
      </div>
    </div>
  );
}
